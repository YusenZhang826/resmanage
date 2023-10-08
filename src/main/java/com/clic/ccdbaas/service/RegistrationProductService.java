package com.clic.ccdbaas.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.RegistrationProductMapper;
import com.clic.ccdbaas.dao.UserMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.RegistrationProduct;
import com.clic.ccdbaas.entity.RegistrationProductExample;
import com.clic.ccdbaas.utils.CompareObjectUtils;
import com.clic.ccdbaas.utils.HttpClientUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationProductService {

    private static final Logger logger = LoggerFactory.getLogger(StorageDeviceService.class);
    @Value("${app.gettoken.url}")
    private String authUrl;
    @Value("${app.info.url}")
    private String appListUrl;
    @Value("${app.info.key}")
    private String key;
    @Value("${app.info.secret}")
    private String secret;
    @Autowired
    CloudVmareService cloudVmareService;
    @Autowired
    RegistrationProductService registrationProductService;
    @Autowired
    private UserMapper userMapper;


    @Value("${oc.cmdb.registrationproduct}")
    private String RegistrationProductClass;
    @Value("${nginx.location.RegistrationProduct}")
    private String RegistrationProductLocation;
    @Value("${nginx.url}")
    private String nginxUrl;
    @Autowired
    private OcService ocService;
    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationProductMapper registrationProductMapper;
    @Autowired
    private ResourceApplicationService resourceApplicationService;

    //注册产品列表获取
    public JSONArray getJson(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, RegistrationProductClass);
    }

    public JSONArray getJson() {
        return ocService.getAllInstanceInfo(RegistrationProductClass);
    }

    public String exportProduct(JSONObject requestParams) {
        JSONArray request = null;
        if (requestParams != null) {
            request = getJson(requestParams);
        } else {
            request = getJson();
        }
        String fileName = System.currentTimeMillis() + ".xlsx";
        String path = RegistrationProductLocation + fileName;
        List<RegistrationProduct> memberList = request.toJavaList(RegistrationProduct.class);
        logger.info("Excel导出注册产品信息" + memberList.toString());
        EasyExcel.write(path, RegistrationProduct.class)
                .sheet("注册产品列表")
                .doWrite(memberList);
        return nginxUrl + fileName;
    }

    /**
     * 查询单个
     *
     * @param instanceId
     */
    public JSONObject getMessage(String instanceId) {
        logger.info("查询设备详情id为" + instanceId);
        JSONObject allInstanceInfo = ocService.getAllInstanceInfo(RegistrationProductClass, instanceId);
        logger.info("设备详情：" + allInstanceInfo);
        return allInstanceInfo;
    }

    //    模拟应用云返回干系人列表
    public String Moki() {
        String json = "[{\"user_name\":\"张磊\",\"employeeNo\":\"18200036\"},{\"user_name\":\"杨敏\",\"employeeNo\":\"18200292\"}]";
        return json;
    }

    public List getProductUserInfo(JSONObject queryParam) {
        JSONArray allAppInfo = getAllAppInfo(queryParam);
        JSONObject singAppJson = allAppInfo.getJSONObject(0);
        JSONArray smUidsArr = singAppJson.getJSONArray("smUids");
        if (smUidsArr.size() > 0) {
            List allProductUserInfo = userService.getUserId(smUidsArr);
            return allProductUserInfo;
        } else {
            return null;
        }
    }


    /**
     * 获取accesstoken
     *
     * @return
     */
    public String getAppToken() {
        JSONObject paramJson = new JSONObject();
        paramJson.put("app_key", key);
        paramJson.put("app_secret", secret);
        System.setProperty("jsse.enableSNIExtension", "false");
        String resInfo = HttpClientUtils.HttpsPostWithJson(authUrl, paramJson.toJSONString(), "utf-8");
        JSONObject tokenJson = JSON.parseObject(resInfo);
        String token = tokenJson.getJSONObject("result").getString("access_token");
        return token;
    }


    /**
     * 获取应用云产品信息
     *
     * @return
     */
    public JSONArray getAllAppInfo(JSONObject queryParam) {
        String token = getAppToken();
        token = "Bearer " + token;
        String firstPageApp = HttpClientUtils.httpsPostWithAuthInfo(appListUrl, queryParam.toJSONString(), token);
        JSONObject firstPageJson = JSON.parseObject(firstPageApp);
        JSONObject dataJson = firstPageJson.getJSONObject("result").getJSONObject("data");
        JSONArray resAppInfoArr = dataJson.getJSONArray("list");
        return resAppInfoArr;
    }


    /**
     * 获取所有页应用云产品信息
     *
     * @return
     */
    public JSONArray getAllACSProductInfo() {
        JSONArray resAppInfoArr = new JSONArray();
        String token = getAppToken();
        token = "Bearer " + token;
        String paramJson = "{}";
        String firstPageApp = HttpClientUtils.httpsPostWithAuthInfo(appListUrl, paramJson, token);
        JSONObject firstPageJson = JSON.parseObject(firstPageApp);
        JSONObject dataJson = firstPageJson.getJSONObject("result").getJSONObject("data");
        String nextPagingId = dataJson.getString("nextPagingId");
        JSONArray firstPageJsonArr = dataJson.getJSONArray("list");
        resAppInfoArr.addAll(firstPageJsonArr);
        while (!StringUtils.isEmpty(nextPagingId)) {
            JSONObject tmpParamJson = new JSONObject();
            tmpParamJson.put("nextPagingId", nextPagingId);
            String tmpPageApp = HttpClientUtils.httpsPostWithAuthInfo(appListUrl, tmpParamJson.toJSONString(), token);
            JSONObject tmpPageJson = JSON.parseObject(tmpPageApp);
            JSONObject tmpDataJson = tmpPageJson.getJSONObject("result").getJSONObject("data");
            nextPagingId = tmpDataJson.getString("nextPagingId");
            JSONArray tmpPageJsonArr = tmpDataJson.getJSONArray("list");
            resAppInfoArr.addAll(tmpPageJsonArr);
        }
        return resAppInfoArr;
    }

    /**
     * 解析应用云产品信息
     *
     * @return
     */
    public List<RegistrationProduct> getACSProductInstance() {
        List<RegistrationProduct> acsProductInstanceList = new ArrayList<>();
        //调取应用云接口获取产品信息
        JSONArray jsonArray = getAllACSProductInfo();
        List<Map> productInfoList = JSONObject.parseArray(jsonArray.toJSONString(), Map.class);

        List<Map> userMapList = userMapper.selectAllSysUserInfo();
        Map<String, String> userInfoMap = userMapList.stream().collect(Collectors.toMap(k -> (String) k.get("employeeNo"), v -> (String) v.get("user_name")));
        for (Map productInfoMap : productInfoList) {
            String ownerEmpNo = (String) productInfoMap.get("owner");
            if (!StringUtils.isEmpty(ownerEmpNo)) {
                if (!StringUtils.isEmpty(userInfoMap.get(ownerEmpNo))) {
                    ownerEmpNo = userInfoMap.get(ownerEmpNo) + "-" + ownerEmpNo;

                    productInfoMap.put("owner", ownerEmpNo);

                }
            }

            //处理人员列表
            if (!StringUtils.isEmpty(productInfoMap.get("dmUids"))) {
                List<String> dmUidsList = (List<String>) productInfoMap.get("dmUids");
                productInfoMap.put("dmUids", resourceApplicationService.generatedString(dealEmpNameInfo(dmUidsList, userInfoMap)));
            }
            if (!StringUtils.isEmpty(productInfoMap.get("dtmUids"))) {
                List<String> dtmUidsList = (List<String>) productInfoMap.get("dtmUids");
                productInfoMap.put("dtmUids", resourceApplicationService.generatedString(dealEmpNameInfo(dtmUidsList, userInfoMap)));
            }
            if (!StringUtils.isEmpty(productInfoMap.get("omUids"))) {
                List<String> omUidsList = (List<String>) productInfoMap.get("omUids");
                productInfoMap.put("omUids", resourceApplicationService.generatedString(dealEmpNameInfo(omUidsList, userInfoMap)));
            }
            if (!StringUtils.isEmpty(productInfoMap.get("smUids"))) {
                List<String> smUidsList = (List<String>) productInfoMap.get("smUids");
                productInfoMap.put("smUids", resourceApplicationService.generatedString(dealEmpNameInfo(smUidsList, userInfoMap)));
            }
            if (!StringUtils.isEmpty(productInfoMap.get("userGroupId"))) {
                List<String> userGroupIdList = (List<String>) productInfoMap.get("userGroupId");
                productInfoMap.put("userGroupId", resourceApplicationService.generatedString(dealEmpNameInfo(userGroupIdList, userInfoMap)));
            }

            //产品token
            if (!StringUtils.isEmpty(productInfoMap.get("token"))) {
                productInfoMap.put("productToken", productInfoMap.get("token"));
                productInfoMap.remove("token");
            }

            //处理描述信息
            if (!StringUtils.isEmpty(productInfoMap.get("detailDesc"))) {
                productInfoMap.put("detailDesc", productInfoMap.get("detailDesc").toString().trim());
            }
            if (!StringUtils.isEmpty(productInfoMap.get("desc"))) {
                productInfoMap.put("desc", productInfoMap.get("desc").toString().trim());
            }

            //map转换成java实体类
            JSONObject jsonObject = new JSONObject(productInfoMap);
            RegistrationProduct registrationProduct = jsonObject.toJavaObject(RegistrationProduct.class);
            acsProductInstanceList.add(registrationProduct);
        }
        return acsProductInstanceList;
    }

    private List<String> dealEmpNameInfo(List<String> originalList, Map<String, String> remoteTenantMap) {
        List resEmpInfoList = new ArrayList();

        for (int i = 0; i < originalList.size(); i++) {
            String empNo = originalList.get(i);
            if (!StringUtils.isEmpty(empNo)) {
                String empName = (String) remoteTenantMap.get(empNo);
                if (!StringUtils.isEmpty(empName)) {
                    empNo = empName + "-" + empNo;
                }
                resEmpInfoList.add(empNo);
            }
        }
        return resEmpInfoList;
    }


    /**
     * 应用云产品信息落库
     *
     * @return
     */
    @XxlJob("updateACSProductInfo")
    public String updateACSProductInfo() {
        int updateCount = 0;
        int insertCount = 0;
        int index;
        try {
            List<RegistrationProduct> productList = getACSProductInstance();
            List<RegistrationProduct> originalProductList = registrationProductMapper.selectByExample(new RegistrationProductExample());
            //远程产品集合  用于判断产品是否已经删除
            Map<String, RegistrationProduct> remoteProductMap = productList.stream().collect(Collectors.toMap(k -> k.getProductToken(), RegistrationProduct -> RegistrationProduct));
            /**
             * 遍历本地所有产品  比对是否已经删除
             */
            for (RegistrationProduct originalProduct : originalProductList) {
                //远程没有  本地还在 说明产品已经删除  本地置为待删除
                if (!remoteProductMap.containsKey(originalProduct.getProductToken())) {
                    originalProduct.setStatus(1);
                    registrationProductMapper.updateByProductToken(originalProduct);
                }
            }


            for (RegistrationProduct registrationProduct : productList) {
                index = 0;
                registrationProduct.setResId(IdUtils.generatedUUID());
                registrationProduct.setLastModified(new Date());

                for (RegistrationProduct originalProduct : originalProductList) {
                    if (originalProduct.getProductToken().equals(registrationProduct.getProductToken())) {
                        index = 1;
                        if (CompareObjectUtils.compareObject(originalProduct, registrationProduct).size() > 2) {
                            registrationProductMapper.updateByProductToken(registrationProduct);
                            updateCount++;
                        }
                        break;
                    }
                }
                if (index == 0) {
                    registrationProductMapper.insertSelective(registrationProduct);
                    insertCount++;
                }
            }
            logger.info("新增应用云产品信息 " + insertCount + " 条数据；" + "更新应用云产品信息 " + updateCount + " 条数据。");
        } catch (Exception e) {
            logger.info("更新应用云产品信息出错");
            throw new RuntimeException(e);
        }
        return "success";
    }

    /**
     * 条件筛选产品列表
     *
     * @param product
     * @return
     */
    public List getProductList(RegistrationProduct product) {
        if (!StringUtils.isEmpty(product.getChangingInfo())) {
            String status = product.getChangingInfo();
            List<String> statusArr = Arrays.asList(status.split(","));
            product.setStatusArr(statusArr);
        }
        List<RegistrationProduct> allInstance = registrationProductMapper.getAllInstance(product);
        return allInstance;
    }

    /**
     * 条件筛选产品列表
     *
     * @param productToken
     * @return
     */
    public JSONObject getProductDetail(String productToken) {
        RegistrationProduct tmpRegistrationProduct = new RegistrationProduct();
        tmpRegistrationProduct.setProductToken(productToken);
        List<RegistrationProduct> productList = registrationProductService.getProductList(tmpRegistrationProduct);
        JSONObject resJson = new JSONObject();
        if (productList.size() > 0) {
            RegistrationProduct registrationProduct = productList.get(0);

            CloudVmare paramVM = new CloudVmare();
            paramVM.setProductToken(productToken);
            List<CloudVmare> allResourceList = cloudVmareService.getAllResourceList(paramVM);
            resJson.put("productInfo", registrationProduct);
            resJson.put("relatedResInfo", allResourceList);
        }


        return resJson;
    }


    /**
     * 条件筛选产品个数
     *
     * @param product
     * @return
     */
    public List getProductCountInfo(RegistrationProduct product) {
        if (!StringUtils.isEmpty(product.getChangingInfo())) {
            String status = product.getChangingInfo();
            List<String> statusArr = Arrays.asList(status.split(","));
            product.setStatusArr(statusArr);
        }
        List<Map> allInstance = registrationProductMapper.getProductCountInfo(product);
        return allInstance;
    }
}
