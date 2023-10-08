package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.conf.AESEncryptor;
import com.clic.ccdbaas.dao.*;
import com.clic.ccdbaas.entity.FileStore;
import com.clic.ccdbaas.entity.ReserveStorage;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.utils.HttpClientUtils;
import com.clic.ccdbaas.utils.PageUtils;
import com.clic.ccdbaas.utils.SSLClient;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("CLOUD_SFS_SHARE")
@ComponentScan({"com.clic.ccdbaas.service"})
public class FileStoreService {
    @Value("${oc.cmdb.filestore}")
    private String fileStoreClassName;
    @Autowired
    private FileStoreMapper fileStoreMapper;
    @Value("${oc.cmdb.network}")
    private String networkClassName;

    @Autowired
    private OcService ocService;
    private static final Logger logger = LoggerFactory.getLogger(FileStoreService.class);
    @Autowired
    private CloudVmNovaMapper cloudVmNovaMapper;
    @Autowired
    private PhysicalMachineMapper physicalMachineMapper;
    @Autowired
    private PhysicalHostMapper physicalHostMapper;
    @Autowired
    private RelationService relationService;
    @Autowired
    private AESEncryptor aesEncryptor;
    @Autowired
    private DetailService detailService;
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private ReserveDeviceService reserveDeviceService;

    /**
     * 将URL信息落库
     */
    public void addSystemLink(JSONArray links) {
        for (int i = 0; i < links.size(); i++) {
            JSONObject link = links.getJSONObject(i);
            HashMap map = new HashMap<>();
            map.put("systemType", link.getString("systemType"));
            map.put("linkUrl", link.getString("linkUrl"));
            System.out.println(link.getString("userName"));
            String user = aesEncryptor.encrypt(link.getString("userName"));
            map.put("userName", user);
            String passwd = aesEncryptor.encrypt(link.getString("password"));
            map.put("password", passwd);
            map.put("extraSpec", link.getString("extraSpec"));
            fileStoreMapper.addSystemLink(map);
        }
    }

    /**
     * 获取云内文件存储信息并落库
     */
    public void addCloudFile2DB(HashMap<String, FileStore> fileMap) {
        String filestr = ocService.getOcClassInstancesByPageSize(fileStoreClassName, 1000);
        int count_update = 0;
        int count_add = 0;

        JSONArray files = (JSONArray) (JSON.parseObject(filestr)).get("objList");
        for (int i = 0; i < files.size(); i++) {
            JSONObject obj = files.getJSONObject(i);
            obj.put("size", obj.getInteger("size"));
            FileStore fileStore = JSON.toJavaObject(obj, FileStore.class);
            if (fileStore.getResId() == null) {
                fileStore.setResId(IdUtils.generatedUUID());
            }
            //设置部署环境字段
            if (fileStore.getBizRegionName().contains("测试")) {
                fileStore.setDeployEnv(0);
            } else {
                fileStore.setDeployEnv(1);
            }
            fileStore.setOwnerName(fileStore.getOwnerName().replace("ExternalDriver_", ""));
            FileStore temp = fileMap.get(fileStore.getNativeId());
            if (temp == null) {
                count_add += 1;
                fileStoreMapper.addFileStore(fileStore);
            } else {
                fileMap.remove(fileStore.getNativeId());
                count_update += 1;
                updateFileStore(fileStore);
            }
        }
        logger.info("云内文件存储信息落库完成,新增" + count_add + "条数据，更新" + count_update + "条数据");
        logger.info("云内文件存储关联关系开始落库");
        addCloudRelation2DB();
        logger.info("云内文件存储关联关系落库完成");
    }

    /**
     * 将云内文件存储关系进行落库
     */
    private void addCloudRelation2DB() {
        //获取文件存储与VPC关联关系的resId
        String relationId = relationService.getResIdByClassName("CLOUD_SFS_SHARE", "CLOUD_ROUTER");
        if (relationId == null) {
            relationId = relationService.addRelation("CLOUD_SFS_SHARE", "CLOUD_ROUTER", "R_SFS_ROUTER", 4, null);
        }

        //获取全部云内文件存储与VPC的关联实例
        List<String> relationIds = new ArrayList<>();
        relationIds.add(relationId);
        List<HashMap> instanceList = relationService.getInstancesByRelationId(relationIds);
        HashMap<String, HashMap> relationMap = new HashMap<>();
        for (HashMap instance : instanceList) {
            relationMap.put(instance.get("sourceInstanceId") + "-" + instance.get("targetInstanceId"), instance);
        }

        //存储文件存储名字和resId对应关系的字典
        HashMap<String, String> fileStoreName2ResId = new HashMap<>();
        List<String> types = new ArrayList<>();
        types.add("cloudFileStore");
        List<HashMap> links = fileStoreMapper.getSystemLinkByType(types);

        for (HashMap link : links) {
            JSONObject extraSpecJson = JSONObject.parseObject((String) link.get("extraSpec"));
            String resourcePoolName = extraSpecJson.getString("resourcePoolName");
            String username = (String) link.get("userName");
            username = aesEncryptor.decrypt(username);
            String password = (String) link.get("password");
            password = aesEncryptor.decrypt(password);
            JSONObject obj = new JSONObject();
            obj.put("username", username);
            obj.put("password", password);
            obj.put("scope", "0");
            String tokenUrl = link.get("linkUrl") + "/deviceManager/v1/rest/xxxxx/sessions";
            HashMap token = getOutToken(tokenUrl, JSONObject.toJSONString(obj));
            String url = link.get("linkUrl") + "/deviceManager/v1/rest";
            String relationClassName = "nfs_share_auth_client";
            String fsquotaClassName = "fsquota";

            //将使用量落库
            String fsquotas = getOutInstance(url, fsquotaClassName, token);
            JSONArray fsquotasJson = (JSONArray) (JSON.parseObject(fsquotas)).get("data");
            for (int i = 0; i < fsquotasJson.size(); i++) {
                JSONObject fsquota = fsquotasJson.getJSONObject(i);
                String sharePath = fsquota.getString("treeName");
                String fileStoreId = fileStoreName2ResId.get(resourcePoolName + sharePath);
                if (fileStoreId == null) {
                    HashMap<String, String> temp = new HashMap<>();
                    temp.put("sharePath", sharePath);
                    temp.put("resourcePoolName", resourcePoolName);
                    fileStoreId = fileStoreMapper.getFileStoreResIdByNameAndPool(temp);
                    fileStoreName2ResId.put(resourcePoolName + sharePath, fileStoreId);
                }
                int usedSize = Integer.parseInt(fsquota.getString("amountUsed").replace("[", "").replace("]", ""));
                FileStore fileStore = new FileStore();
                fileStore.setUsedSize(usedSize);
                fileStore.setResId(fileStoreId);
                updateFileStore(fileStore);
            }

            //将文件存储与可访问网段的关联关系落库
            String relations = getOutInstance(url, relationClassName, token);
            JSONArray relationsJson = (JSONArray) (JSON.parseObject(relations)).get("data");
            for (int i = 0; i < relationsJson.size(); i++) {
                JSONObject extraSpec = new JSONObject();
                String access;
                JSONObject relation = relationsJson.getJSONObject(i);
                String sharePath = relation.getString("sharePath");
                String vpcId = relation.getString("name").split("#")[1];
                //获取可访问子网
                JSONObject subnet = detailService.getRouterSubnetVFW(vpcId, networkClassName, 1, 1000);
                JSONArray networks = new JSONArray();
                if (subnet != null) {
                    networks = subnet.getJSONArray("objList");
                    String ips = "";
                    for (int j = 0; j < networks.size(); j++) {
                        ips += networks.getJSONObject(j).getString("cidr") + ";";
                    }
                    if (ips.length() > 0) {
                        ips = ips.substring(0, ips.length() - 1);
                    }
                    extraSpec.put("ip", ips);
                    if (relation.getString("accessVal").equals("0")) {
                        access = "只读";
                    } else if (relation.getString("accessVal").equals("1")) {
                        access = "读写";
                    } else {
                        access = "读、写(不能删除、重命名)";
                    }
                    extraSpec.put("access", access);

                    String fileStoreId = fileStoreName2ResId.get(resourcePoolName + sharePath);
                    if (fileStoreId == null) {
                        HashMap<String, String> temp = new HashMap<>();
                        temp.put("sharePath", sharePath);
                        temp.put("resourcePoolName", resourcePoolName);
                        fileStoreId = fileStoreMapper.getFileStoreResIdByNameAndPool(temp);
                        fileStoreName2ResId.put(resourcePoolName + sharePath, fileStoreId);
                    }
                    addRelationInstance(fileStoreId, vpcId, relationId, JSONObject.toJSONString(extraSpec), relationMap);
                }
            }
        }
        //清理已经不存在的文件关联关系
        for (HashMap map : relationMap.values()) {
            relationMapper.deleteInstanceByResId((String) map.get("resId"));
        }
    }

    private void updateFileStore(FileStore fileStore) {
        fileStore.setLast_Modified(new Date());
        fileStoreMapper.updateFileStore(fileStore);
    }

    /**
     * 获取全量的文件存储信息
     */
    public List<FileStore> getAllFileStore(FileStore fileStore) {
        if (fileStore.getStatus() != null) {
            if (fileStore.getStatus().equals("可用")) {
                fileStore.setStatus("available");
            } else if (fileStore.getStatus().equals("不可用")) {
                fileStore.setStatus("inactive");
            }
        }
        List<FileStore> files = fileStoreMapper.getAllFileStore(fileStore);
        if (fileStore.getIps() != null) {
            List<FileStore> res = new ArrayList<>();
            for (FileStore file : files) {
                String ip = getRelatedIp(file.getResId());
                if (ip.contains(fileStore.getIps())) {
                    file.setIps(ip);
                    res.add(file);
                }
            }
            files = res;
        } else {
            for (FileStore file : files) {
                file.setIps(getFileStoreByResId(file.getResId()).getIps());
            }
        }
        return files;
    }

    /**
     * 获取单个文件存储详情
     */
    public FileStore getFileStoreByResId(String resId) {
        FileStore file = fileStoreMapper.getFileStoreByResId(resId);
        if (file != null) {
            String fileIp = getRelatedIp(resId);
            file.setIps(fileIp);
        }
        return file;
    }

    private String getRelatedIp(String resId) {
        List<String> className = new ArrayList<>();
        className.add("CLOUD_HOST");
        className.add("SYS_X86Server");
        className.add("CLOUD_VM_NOVA");
        className.add("CLOUD_ROUTER");
        List<String> extraSpecs = relationService.getExtraSpecBySourceIdAndTargetClassName(resId, className, "CLOUD_SFS_SHARE");
        String fileIp = "";
        if (extraSpecs != null && extraSpecs.size() != 0) {
            for (String extraSpec : extraSpecs) {
                JSONObject obj = JSONObject.parseObject(extraSpec);
                fileIp += obj.getString("ip") + ";";
            }
            fileIp = fileIp.substring(0, fileIp.length() - 1);
        }
        return fileIp;
    }

    /**
     * 将云外文件存储落库
     */
    public void addOutFile2OB(HashMap<String, FileStore> fileMap) {
        List<String> types = new ArrayList<>();
        types.add("fileStore");
        List<HashMap> links = fileStoreMapper.getSystemLinkByType(types);

        for (HashMap link : links) {
            JSONObject extraSpec = JSONObject.parseObject((String) link.get("extraSpec"));
            String systemName = extraSpec.getString("systemName");
            String username = (String) link.get("userName");
            username = aesEncryptor.decrypt(username);
            String password = (String) link.get("password");
            password = aesEncryptor.decrypt(password);
            JSONObject obj = new JSONObject();
            obj.put("username", username);
            obj.put("password", password);
            obj.put("scope", "0");

            String tokenUrl = link.get("linkUrl") + "/deviceManager/rest/xxxxx/sessions";
            HashMap token = getOutToken(tokenUrl, JSONObject.toJSONString(obj));
            String url = link.get("linkUrl") + "/deviceManager/rest";
            String className = "filesystem";
            String filestr = getOutInstance(url, className, token);

            JSONArray files = (JSONArray) (JSON.parseObject(filestr)).get("data");
            HashMap<String, Object> map = new HashMap<>();
            for (int i = 0; i < files.size(); i++) {
                JSONObject file = files.getJSONObject(i);
                String Id = file.getString("ID");
                map.put(Id, file);
            }
            addOutFile2ObByFileClass("NFS", map, url, token, systemName, fileMap);
            addOutFile2ObByFileClass("CIFS", map, url, token, systemName, fileMap);
            logger.info("云外文件存储信息落库完成");
            logger.info("云外文件存储关联关系开始落库");
            addOutRelation2DB(url, token);

        }
    }

    /**
     * 根据具体文件类型获取对应的云外文件存储并落库
     * fileClass为文件类型名称，map为存储了文件系统的字典
     */
    public void addOutFile2ObByFileClass(String fileClass, HashMap map, String url, HashMap token, String systemName, HashMap<String, FileStore> fileMap) {
        String filestr;
        if (fileClass.equals("NFS")) {
            String className = "NFSHARE";
            filestr = getOutInstance(url, className, token);
        } else {
            String className = "CIFSHARE";
            filestr = getOutInstance(url, className, token);
        }
        JSONArray files = (JSONArray) (JSON.parseObject(filestr)).get("data");
        for (int i = 0; i < files.size(); i++) {
            JSONObject file = files.getJSONObject(i);
            JSONObject parentFile = (JSONObject) map.get(file.getString("FSID"));
            if (parentFile != null) {
                FileStore fileStore = new FileStore();
                fileStore.setNativeId(token.get("deviceId") + "-" + fileClass + "-" + file.getString("ID"));
                fileStore.setCreateAt(new Date());
                fileStore.setShareProto(fileClass);
                fileStore.setTenantName("中国人寿");
                fileStore.setSharePath(file.getString("SHAREPATH"));
                fileStore.setLast_Modified(new Date());
                fileStore.setName(file.getString("NAME"));
                fileStore.setOwnerName(systemName);
                fileStore.setDeployEnv(0);
                if (parentFile != null) {
                    int size = (int) (Long.parseLong(parentFile.getString("CAPACITY")) / 2097152);
                    fileStore.setSize(size);
                    int usedSize = (int) (Long.parseLong(parentFile.getString("ALLOCCAPACITY")) / 2048);
                    fileStore.setUsedSize(usedSize);
                    if ((parentFile.getString("HEALTHSTATUS")).equals("1")) {
                        fileStore.setStatus("available");
                    } else {
                        fileStore.setStatus("unavailable");
                    }
                }
                FileStore temp = fileMap.get(fileStore.getNativeId());
                if (temp == null) {
                    fileStore.setResId(IdUtils.generatedUUID());
                    fileStoreMapper.addFileStore(fileStore);
                } else {
                    fileStore.setResId(temp.getResId());
                    updateFileStore(fileStore);
                    fileMap.remove(fileStore.getNativeId());
                }
            }
        }
    }

    /**
     * 进行云外身份认证
     */
    public HashMap getOutToken(String url, String jsonBody) {
        HashMap<String, Object> map = new HashMap<>();
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        JSONObject data = new JSONObject();
        try {
            System.setProperty("jsse.enableSNIExtension", "false");
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            StringEntity se = new StringEntity(jsonBody);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            Header[] heads = response.getAllHeaders();
            for (Header h : heads) {
                if (h.getName().equals("Set-Cookie")) {
                    String cookie = (h.getValue()).split(";")[0];
                    map.put("Cookie", cookie);
                }
            }
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                    System.out.println("返回结果为" + result);
                    data = (JSONObject) (JSON.parseObject(result)).get("data");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        map.put("iBaseToken", data.get("iBaseToken"));
        HashMap res = new HashMap<>();
        res.put("header", map);
        res.put("deviceId", data.get("deviceid"));
        return res;
    }


    /**
     * 调用云外查询接口
     */
    public String getOutInstance(String link, String className, HashMap headers) {
        String url = link + "/" + headers.get("deviceId") + "/" + className;
        return HttpClientUtils.HttpsGetWithHeader(url, (Map<String, Object>) headers.get("header"));
    }

    /**
     * 将云外NFS关联关系落库
     */
    public void addOutRelation2DB(String url, HashMap token) {
        String className = "NFS_SHARE_AUTH_CLIENT";
        String filestr = getOutInstance(url, className, token);
        JSONArray files = (JSONArray) (JSON.parseObject(filestr)).get("data");

        //获取文件存储与物理机、宿主机、虚机的关联Id
        String fileNovaRelationId = relationService.getResIdByClassName("CLOUD_SFS_SHARE", "CLOUD_VM_NOVA");
        if (fileNovaRelationId == null) {
            fileNovaRelationId = relationService.addRelation("CLOUD_SFS_SHARE", "CLOUD_VM_NOVA", "R_SFS_Nova", 4, null);
        }
        String fileHostRelationId = relationService.getResIdByClassName("CLOUD_SFS_SHARE", "CLOUD_HOST");
        if (fileHostRelationId == null) {
            fileHostRelationId = relationService.addRelation("CLOUD_SFS_SHARE", "CLOUD_HOST", "R_SFS_Host", 4, null);
        }
        String fileServerRelationId = relationService.getResIdByClassName("CLOUD_SFS_SHARE", "SYS_X86Server");
        if (fileServerRelationId == null) {
            fileServerRelationId = relationService.addRelation("CLOUD_SFS_SHARE", "SYS_X86Server", "R_SFS_Server", 4, null);
        }

        //获取全部云外文件存储的关联实例
        List<String> relationId = new ArrayList<>();
        relationId.add(fileNovaRelationId);
        relationId.add(fileHostRelationId);
        relationId.add(fileServerRelationId);
        List<HashMap> instanceList = relationService.getInstancesByRelationId(relationId);
        HashMap<String, HashMap> relationMap = new HashMap<>();
        for (HashMap instance : instanceList) {
            relationMap.put(instance.get("sourceInstanceId") + "-" + instance.get("targetInstanceId"), instance);
        }

        for (int i = 0; i < files.size(); i++) {
            JSONObject extraSpec = new JSONObject();
            String ip = files.getJSONObject(i).getString("NAME");
            extraSpec.put("ip", ip);
            String nativeId = token.get("deviceId") + "-" + "NFS" + "-" + files.getJSONObject(i).getString("PARENTID");
            String accessVal = "";
            if (files.getJSONObject(i).getString("ACCESSVAL").equals("0")) {
                accessVal = "只读";
            } else {
                accessVal = "读写";
            }
            extraSpec.put("access", accessVal);
            String fileId = fileStoreMapper.getFileStoreResIdByNativeId(nativeId);
            JSONObject relationObject = getMachineResIdByIp(ip);
            if (relationObject != null) {
                if (relationObject.getString("className").equals("CLOUD_VM_NOVA")) {
                    addRelationInstance(fileId, relationObject.getString("resId"), fileNovaRelationId, JSONObject.toJSONString(extraSpec), relationMap);
                }
                if (relationObject.getString("className").equals("SYS_X86Server")) {
                    addRelationInstance(fileId, relationObject.getString("resId"), fileServerRelationId, JSONObject.toJSONString(extraSpec), relationMap);
                }
                if (relationObject.getString("className").equals("CLOUD_HOST")) {
                    addRelationInstance(fileId, relationObject.getString("resId"), fileHostRelationId, JSONObject.toJSONString(extraSpec), relationMap);
                }
            }
            //未在三表中找到的IP作为虚机,并增加existFlag字段作为后续清洗标志
            else {
                extraSpec.put("existFlag", "False");
                HashMap<String, Object> fileMap = new HashMap<>();
                fileMap.put("sourceInstanceId", fileId);
                fileMap.put("relationId", fileNovaRelationId);
                fileMap.put("lastModified", new Date());
                fileMap.put("extraSpec", JSONObject.toJSONString(extraSpec));
                Boolean flag = false;
                Iterator<Map.Entry<String, HashMap>> iterator = relationMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, HashMap> entry = iterator.next();
                    //存在则更新
                    HashMap map = entry.getValue();
                    if (map.get("sourceInstanceId").equals(fileId) && map.get("relationId").equals(fileNovaRelationId) && JSONObject.parseObject((String) map.get("extraSpec")).getString("ip").equals(ip)) {
                        if (!map.get("extraSpec").equals(extraSpec)) {
                            fileMap.put("targetInstanceId", map.get("targetInstanceId"));
                            fileMap.put("resId", map.get("resId"));
                            relationMapper.updateRelationInstance(fileMap);
                        }
                        relationMap.remove(entry.getKey());
                        flag = true;
                        break;
                    }
                }
                //不存在则添加
                if (!flag) {
                    fileMap.put("targetInstanceId", IdUtils.generatedUUID());
                    String resId = IdUtils.generatedUUID();
                    fileMap.put("resId", resId);
                    relationMapper.addRelationInstance(fileMap);
                }
            }
        }
        //清理已经不存在的文件关联关系
        for (HashMap map : relationMap.values()) {
            relationMapper.deleteInstanceByResId((String) map.get("resId"));
        }
        logger.info("云外NFS关联关系已落库");
    }

    public List<HashMap> getFileStoreRelationByResId(String resId) {
        return fileStoreMapper.getFileStoreRelationByResId(resId);
    }

    public JSONObject getMachineResIdByIp(String ip) {
        JSONObject obj = new JSONObject();
        List<HashMap> maps = cloudVmNovaMapper.getResIdByIp(ip);
        for (HashMap map : maps) {
            if (map.get("relatedIp") != null) {
                String[] relateIps = map.get("relatedIp").toString().split(";");
                for (int i = 0; i < relateIps.length; i++) {
                    if (relateIps[i].equals(ip)) {
                        obj.put("className", "CLOUD_VM_NOVA");
                        obj.put("resId", map.get("resId"));
                        return obj;
                    }
                }
            }
            if (map.get("virtualIp") != null) {
                String[] virtualIps = map.get("virtualIp").toString().split(";");
                for (int i = 0; i < virtualIps.length; i++) {
                    if (virtualIps[i].equals(ip)) {
                        obj.put("className", "CLOUD_VM_NOVA");
                        obj.put("resId", map.get("resId"));
                        return obj;
                    }
                }
            }
            if (map.get("mainIp").equals(ip)) {
                obj.put("className", "CLOUD_VM_NOVA");
                obj.put("resId", map.get("resId"));
                return obj;
            }
        }

        maps = physicalMachineMapper.getResIdByIp(ip);
        for (HashMap map : maps) {
            if (map.get("relateIp") != null) {
                String[] relateIps = map.get("relateIp").toString().split(";");
                for (int i = 0; i < relateIps.length; i++) {
                    if (relateIps[i].equals(ip)) {
                        obj.put("className", "SYS_X86Server");
                        obj.put("resId", map.get("resId"));
                        return obj;
                    }
                }
            }
            if (map.get("virtualIp") != null) {
                String[] virtualIps = map.get("virtualIp").toString().split(";");
                for (int i = 0; i < virtualIps.length; i++) {
                    if (virtualIps[i].equals(ip)) {
                        obj.put("className", "SYS_X86Server");
                        obj.put("resId", map.get("resId"));
                        return obj;
                    }
                }
            }
            if (map.get("mainIp").equals(ip)) {
                obj.put("className", "SYS_X86Server");
                obj.put("resId", map.get("resId"));
                return obj;
            }
        }

        maps = physicalHostMapper.getResIdByIp(ip);
        for (HashMap map : maps) {
            if (map.get("relatedIp") != null) {
                String[] relatedIps = map.get("relatedIp").toString().split(";");
                for (int i = 0; i < relatedIps.length; i++) {
                    if (relatedIps[i].equals(ip)) {
                        obj.put("className", "CLOUD_HOST");
                        obj.put("resId", map.get("resId"));
                        return obj;
                    }
                }
            }
            if (map.get("virtualIp") != null) {
                String[] virtualIps = map.get("virtualIp").toString().split(";");
                for (int i = 0; i < virtualIps.length; i++) {
                    if (virtualIps[i].equals(ip)) {
                        obj.put("className", "CLOUD_HOST");
                        obj.put("resId", map.get("resId"));
                        return obj;
                    }
                }
            }
            if (map.get("mainIp").equals(ip)) {
                obj.put("className", "CLOUD_HOST");
                obj.put("resId", map.get("resId"));
                return obj;
            }
        }
        return null;
    }

    public TableDataInfo getAllInstance() {
        List<FileStore> list = getAllFileStore(null);
        TableDataInfo res = PageUtils.getDataTable(list);
        return res;
    }

    public JSONObject getInstanceDetail(String resId) {
        FileStore res = getFileStoreByResId(resId);
        JSONObject detail = JSONObject.parseObject(JSON.toJSONString(res));
        String param;
        if (res != null && res.getResourcePoolName() != null) {
            param = fileStoreMapper.getSystemLinkByExtraSpec(res.getResourcePoolName());
        } else {
            //将来云外接入更多文件存储时，需要进行别的条件筛选
            param = fileStoreMapper.getSystemLinkByExtraSpec(res.getOwnerName());
        }
        if (param != null) {
            JSONObject paramObject = JSONObject.parseObject(param);
            List<String> storageId = new ArrayList<>();
            storageId.add(paramObject.getString("reserveStorageId"));
            List<ReserveStorage> storage = reserveDeviceService.getReserveStorageByIds(storageId);
            if (storage != null) {
                detail.put("reserveStorage", storage.get(0));
            }
        }
        return detail;
    }

    /**
     * 数据清洗的内容：清洗将云外文件存储的关联IP，检查是否存在IP新绑定虚机、物理机、宿主机
     */
    public void dataClean() {
        //更新文件存储来源存储设备
        addReserveStorageRelation();
        //获取文件存储与物理机、宿主机、虚机的关联Id
        String fileNovaRelationId = relationService.getResIdByClassName("CLOUD_SFS_SHARE", "CLOUD_VM_NOVA");
        if (fileNovaRelationId == null) {
            fileNovaRelationId = relationService.addRelation("CLOUD_SFS_SHARE", "CLOUD_VM_NOVA", "R_SFS_Nova", 4, null);
        }
        String fileHostRelationId = relationService.getResIdByClassName("CLOUD_SFS_SHARE", "CLOUD_HOST");
        if (fileHostRelationId == null) {
            fileHostRelationId = relationService.addRelation("CLOUD_SFS_SHARE", "CLOUD_HOST", "R_SFS_Host", 4, null);
        }
        String fileServerRelationId = relationService.getResIdByClassName("CLOUD_SFS_SHARE", "SYS_X86Server");
        if (fileServerRelationId == null) {
            fileServerRelationId = relationService.addRelation("CLOUD_SFS_SHARE", "SYS_X86Server", "R_SFS_Server", 4, null);
        }

        //获取全部未绑定的关联IP
        List<String> relationId = new ArrayList<>();
        relationId.add(fileNovaRelationId);
        relationId.add(fileHostRelationId);
        relationId.add(fileServerRelationId);

        List<HashMap> instanceList = relationService.getInstancesByRelationIdAndExtraSpec(relationId, "existFlag");
        for (HashMap instance : instanceList) {
            JSONObject extraSpec = JSONObject.parseObject(String.valueOf(instance.get("extraSpec")));
            String ip = extraSpec.getString("ip");
            JSONObject relationObject = getMachineResIdByIp(ip);
            //若IP新入库则更新
            if (relationObject != null) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("resId", instance.get("resId"));
                map.put("targetInstanceId", relationObject.getString("resId"));
                map.put("lastModified", new Date());
                extraSpec.remove("existFlag");
                map.put("extraSpec", JSONObject.toJSONString(extraSpec));
                if (relationObject.getString("className").equals("CLOUD_VM_NOVA")) {
                    map.put("relationId", fileNovaRelationId);
                }
                if (relationObject.getString("className").equals("SYS_X86Server")) {
                    map.put("relationInd", fileServerRelationId);
                }
                if (relationObject.getString("className").equals("CLOUD_HOST")) {
                    map.put("relationId", fileHostRelationId);
                }
                relationMapper.updateRelationInstance(map);
            }
        }
    }

    public void addFile2DB() {
        //获取t_file_store表中全部nativeId
        List<FileStore> list = fileStoreMapper.getAllFileStore(null);
        HashMap<String, FileStore> fileMap = new HashMap<>();
        for (FileStore file : list) {
            fileMap.put(file.getNativeId(), file);
        }

        //云内落库
        addCloudFile2DB(fileMap);
        //云外落库
        addOutFile2OB(fileMap);

        //清理已经不存在的文件
        Iterator<String> iterator = fileMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            fileStoreMapper.deleteFileStoreByNativeId(key);
        }
    }

    public void addRelationInstance(String sourceInstanceId, String targetInstanceId, String relationId, String extraSpec, HashMap<String, HashMap> relationMap) {
        HashMap<String, Object> map = new HashMap<>();
        String resId = IdUtils.generatedUUID();
        map.put("resId", resId);
        map.put("sourceInstanceId", sourceInstanceId);
        map.put("targetInstanceId", targetInstanceId);
        map.put("relationId", relationId);
        map.put("lastModified", new Date());
        map.put("extraSpec", extraSpec);
        if (relationMap.get(sourceInstanceId + "-" + targetInstanceId) == null) {
            relationMapper.addRelationInstance(map);
        } else {
            map.put("resId", relationMap.get(sourceInstanceId + "-" + targetInstanceId));
            relationMapper.updateRelationInstance(map);
            relationMap.remove(sourceInstanceId + "-" + targetInstanceId);
        }
    }

    public void instanceClean() {
        List<HashMap> list = relationMapper.getAllRelationInstance();
        for (HashMap map : list) {
            String extra = (String) map.get("extraSpec");
            if (extra.contains("\\")) {
                map.put("extraSpec", extra.replace("\\", ""));
                relationMapper.updateRelationInstance(map);
            }
        }
    }

    public int getResCount() {
        return fileStoreMapper.getFileStoreCount();
    }

    public void addReserveStorageRelation() {
        List<String> types = new ArrayList<>();
        types.add("cloudFileStore");
        types.add("fileStore");
        List<HashMap> links = fileStoreMapper.getSystemLinkByType(types);
        for (HashMap link : links) {
            String linkUrl = (String) link.get("linkUrl");
            String extraSpec = (String) link.get("extraSpec");
            JSONObject param = JSON.parseObject(extraSpec);
            String ip = linkUrl.replace("https://", "").split(":")[0];
            List<HashMap> storages = fileStoreMapper.selectReserveStorageResIdByIp(ip);
            OUT:
            for (HashMap storage : storages) {
                String[] ips = ((String) storage.get("ip")).split(", ");
                for (int i = 0; i < ips.length; i++) {
                    if (ips[i].equals(ip)) {
                        param.put("reserveStorageId", storage.get("resId"));
                        link.put("extraSpec", JSONObject.toJSONString(param));
                        fileStoreMapper.updateSystemLinkExtraSpec(link);
                        break OUT;
                    }
                }
            }
        }
    }
}
