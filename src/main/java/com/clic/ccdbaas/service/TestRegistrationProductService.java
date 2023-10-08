package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.clic.ccdbaas.dao.RegistrationProductMapper;
import com.clic.ccdbaas.dao.TestRegistrationProductMapper;
import com.clic.ccdbaas.entity.RegistrationProduct;
import com.clic.ccdbaas.entity.RegistrationProductExample;
import com.clic.ccdbaas.entity.TestRegistrationProduct;
import com.clic.ccdbaas.utils.HttpClientUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("Test_Registration_Product")
public class TestRegistrationProductService {
    @Value("${fourA.test.api}")
    private String api;
    @Value("${fourA.test.rule.url}")
    private String ruleUrl;
    @Value("${fourA.test.user.url}")
    private String userUrl;
    @Value("${fourA.test.host.url}")
    private String hostUrl;
    @Value("${fourA.test.depart.url}")
    private String departUrl;
    @Value("${fourA.test.user.passwd}")
    private String passwd;
    @Autowired
    private TestRegistrationProductMapper testRegistrationProductMapper;
    @Autowired
    private RegistrationProductMapper registrationProductMapper;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(TestRegistrationProductService.class);

    @XxlJob("updateTestRegistrationProduct")
    public void addFromRegistrationProduct() {
        List<RegistrationProduct> originalProductList = registrationProductMapper.selectByExample(new RegistrationProductExample());
        for (RegistrationProduct registrationProduct : originalProductList) {
            TestRegistrationProduct product = JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(registrationProduct)), TestRegistrationProduct.class);
            product.setDescription(registrationProduct.getDesc());
            product.setName(product.getName());
            product.setProductionToken(registrationProduct.getProductToken());
            LambdaQueryWrapper<TestRegistrationProduct> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(TestRegistrationProduct::getProductionToken, product.getProductionToken());
            TestRegistrationProduct temp = testRegistrationProductMapper.selectOne(wrapper);
            if (temp != null) {
                product.setResId(temp.getResId());
                QueryWrapper<TestRegistrationProduct> wr = new QueryWrapper<>();
                wr.eq("resId", product.getResId());
                testRegistrationProductMapper.update(product, wr);
            } else {
                product.setResId(IdUtils.generatedUUID());
                testRegistrationProductMapper.insert(product);
            }
        }
    }

    public List<TestRegistrationProduct> getAllRegistrationProduct(TestRegistrationProduct testRegistrationProduct) {
        return testRegistrationProductMapper.getAllInstance(testRegistrationProduct);
    }

    public void addTestRegistrationProduct(JSONArray array) {
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            HashMap<String, Object> map = new HashMap<>();
            map.put("resId", object.getString("resId"));
            map.put("belongProduct", object.getString("belongProduct"));
            map.put("productToken", object.getString("productToken"));
            if (object.getString("className").equals("CLOUD_VM_NOVA")) {
                testRegistrationProductMapper.addVmProduct(map);
            } else {
                testRegistrationProductMapper.addPhysicalProduct(map);
            }
        }
    }

    public void addProductAuthorization() {
        HashMap<String, Object> headers = new HashMap<>();
        Locale alocale = Locale.US;
        DateFormat fmt = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss z", new DateFormatSymbols(alocale));
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        headers.put("Date", fmt.format(new Date()));
        headers.put("AccessToken", api);
        //----获取所有符合授权条件的主机
        List<Map<String, Object>> list = testRegistrationProductMapper.getAuthorizeProduct();
        for (Map<String, Object> map : list) {
            try {
                String[] stakeholders = ((String) map.get("stakeholder")).split(",");
                for (int i = 0; i < stakeholders.length; i++) {
                    add4AAuthrization((String) map.get("mainIp"), stakeholders[i], (String) map.get("deployOs"), "test");
                }
                add4AAuthrization((String) map.get("mainIp"), (String) map.get("deployAdminA"), (String) map.get("deployOs"), "test");
                logger.info("在测试4A中成功为" + map.get("mainIp") + "进行授权");
            } catch (Exception e) {
                logger.error("在测试4A中为" + map.get("mainIp") + "进行授权时出错");
                e.printStackTrace();
            }
        }
    }

    private void add4AAuthrization(String ip, String user, String deployOs, String account) {
        HashMap<String, Object> headers = new HashMap<>();
        Locale alocale = Locale.US;
        DateFormat fmt = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss z", new DateFormatSymbols(alocale));
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        headers.put("Date", fmt.format(new Date()));
        headers.put("AccessToken", api);
        Boolean hostFlag = Boolean.FALSE;
        String hostName = "";
        int hostId = -1;
        String userName = user.split("-")[0];
        String userNum = user.split("-")[1];
        String ruleResponse = HttpClientUtils.HttpsGetWithHeader(ruleUrl + "?ruleName=" + userNum, headers);
        JSONArray rules = new JSONArray();
        if (JSONObject.parseObject(ruleResponse).containsKey("rules")) {
            JSONObject.parseObject(ruleResponse).getJSONArray("rules");
        }
        for (int i = 0; i < rules.size(); i++) {
            JSONObject rule = rules.getJSONObject(i);
            if (ObjectUtils.equals(rule.getString("accountName"), account) && ObjectUtils.equals(rule.getString("hostIp"), ip) &&
                    ObjectUtils.equals(rule.getString("username"), userNum)) {
                return;
            }
        }
        Map userInfo = userService.getUserCardInfoByEmpNo(userNum);
        //----获取部门Id
        String departList = HttpClientUtils.HttpsGetWithHeader(departUrl, headers);
        JSONArray departs = new JSONArray();
        if (JSONObject.parseObject(departList).containsKey("departments")) {
            departs = JSONObject.parseObject(departList).getJSONArray("departments");
        }
        int departId = -1;
        for (int i = 0; i < departs.size(); i++) {
            JSONObject depart = departs.getJSONObject(i);
            if (ObjectUtils.equals(depart.getString("departmentName"), userInfo.get("dept2"))) {
                departId = depart.getInteger("departmentId");
                break;
            }
        }
        //查询用户是否存在，不存在则为该用户进行创建
        Boolean userFlag = Boolean.FALSE;
        String userResponse = HttpClientUtils.HttpsGetWithHeader(userUrl + "?username=" + userNum, headers);
        JSONArray users = new JSONArray();
        if (JSONObject.parseObject(userResponse).containsKey("users")) {
            users = JSONObject.parseObject(userResponse).getJSONArray("users");
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.getJSONObject(i).getString("username").equals(userNum)) {
                userFlag = Boolean.TRUE;
                break;
            }
        }
        //创建该用户
        if (!userFlag) {
            //-----把邮箱进行获取
            JSONObject body = new JSONObject();
            body.put("departmentId", departId);
            body.put("email", userInfo.get("email"));
            body.put("nickname", userName);
            body.put("password", passwd);
            //---设置用户角色
            body.put("roleName", "运维员");
            body.put("username", userNum);
            //----暂时用原有的post方法，若不可以则需要添加contenet-length
            Map<String, String> header = new HashMap<>();
            header.put("AccessToken", api);
            String res = HttpClientUtils.HttpsPostWithHeader(userUrl, body.toJSONString(), header, "utf-8");
            System.out.println("建立用户返回结果" + res);
        }
        //查询主机是否存在，不存在则需要创建
        String hostResponse = HttpClientUtils.HttpsGetWithHeader(hostUrl + "?host=" + ip, headers);
        JSONArray hosts = new JSONArray();
        if (JSONObject.parseObject(hostResponse).containsKey("hosts")) {
            hosts = JSONObject.parseObject(hostResponse).getJSONArray("hosts");
        }
        for (int i = 0; i < hosts.size(); i++) {
            JSONObject host = hosts.getJSONObject(i);
            if (ObjectUtils.equals(host.getString("hostIp"), ip)) {
                hostFlag = Boolean.TRUE;
                hostName = host.getString("hostname");
                hostId = host.getInteger("hostId");
                System.out.println("机器已存在");
                break;
            }
        }
        if (!hostFlag) {
            JSONObject body = new JSONObject();
            body.put("departmentId", departId);
            body.put("hostIp", ip);
            hostName = userName + "_" + ip;
            body.put("hostname", hostName);
            if (deployOs != null) {
                deployOs = deployOs.toLowerCase();
            }
            if (deployOs.contains("centos")) {
                if (deployOs.contains(("7.5"))) {
                    body.put("operatingSystem", "Centos7.5");
                } else {
                    body.put("operatingSystem", "CentOS");
                }
            } else if (deployOs.contains("ubuntu")) {
                body.put("operatingSystem", "Ubuntu");
            } else if (deployOs.contains("red")) {
                if (deployOs.contains("7.2")) {
                    body.put("operatingSystem", "Redhat7.2");
                } else if (deployOs.contains("7.4")) {
                    body.put("operatingSystem", "Redhat7.4");
                } else if (deployOs.contains("6.7")) {
                    body.put("operatingSystem", "Redhat6.7");
                } else if (deployOs.contains("7.7")) {
                    body.put("operatingSystem", "Redhat7.7");
                } else if (deployOs.contains("7.9")) {
                    body.put("operatingSystem", "Redhat7.9");
                } else {
                    body.put("operatingSystem", "Red Hat");
                }
            } else if (deployOs.contains("kylin")) {
                body.put("operatingSystem", "KylinV10SP1");
            } else if (deployOs.contains("debian")) {
                body.put("operatingSystem", "Debian");
            } else if (deployOs.contains("suse")) {
                body.put("operatingSystem", "SUSE Linux");
            } else if (deployOs.contains("windows 7")) {
                body.put("operatingSystem", "Windows 7");
            } else if (deployOs.contains("windows 8")) {
                body.put("operatingSystem", "Windows 8");
            } else if (deployOs.contains("windows 10")) {
                body.put("operatingSystem", "Windows 10");
            } else if (deployOs.contains("windows")) {
                if (deployOs.contains("2008")) {
                    body.put("operatingSystem", "Windows Server 2008");
                } else if (deployOs.contains("2012")) {
                    body.put("operatingSystem", "Windows Server 2012");
                } else if (deployOs.contains("2016")) {
                    body.put("operatingSystem", "Windows Server 2016");
                } else if (deployOs.contains("2019")) {
                    body.put("operatingSystem", "Windows Server 2019");
                } else {
                    body.put("operatingSystem", "Other Windows");
                }
            } else {
                body.put("operatingSystem", "Other");
            }
            Map<String, String> header = new HashMap<>();
            header.put("AccessToken", api);
            System.out.println("host：" + hostUrl + "     body:" + body.toJSONString() + "   header:" + header);
            JSONObject response = JSON.parseObject(HttpClientUtils.HttpsPostWithHeader(hostUrl, body.toJSONString(), header, "utf-8"));
            System.out.println("建立机器结果" + JSONObject.toJSONString(response));
            hostId = response.getInteger("hostId");
        }
        //查询主机下的全部账户，不存在则为其新建账户

        String accountList = HttpClientUtils.HttpsGetWithHeader(hostUrl + "/" + hostId + "/" + "accounts", headers);
        JSONArray accounts = new JSONArray();
        if (JSONObject.parseObject(accountList).containsKey("accounts")) {
            accounts = JSONObject.parseObject(accountList).getJSONArray("accounts");
        }
        Boolean accountFlag = Boolean.FALSE;
        String protocol = "";
        //通过机器操作系统生成协议类型
        if (deployOs.toLowerCase().contains("windows")) {
            protocol = "RDP";
        } else {
            protocol = "SSH";
        }
        if (accounts != null) {
            for (int i = 0; i < accounts.size(); i++) {
                JSONObject ac = accounts.getJSONObject(i);
                System.out.println("账户为" + JSONObject.toJSONString(ac));
                if (ObjectUtils.equals(ac.getString("accountName"), account) && ObjectUtils.equals(ac.getString("protocol"), protocol)) {
                    accountFlag = Boolean.TRUE;
                    break;
                }
            }
        }
        if (!accountFlag) {
            JSONObject body = new JSONObject();
            body.put("accountName", account);
            //-----查看登陆方式是什么
            body.put("authMode", "autoLogin");
            body.put("protocol", protocol);
            //----暂时用原有的post方法，若不可以则需要添加contenet-length
            System.out.println(hostUrl + hostId + "accounts");
            Map<String, String> header = new HashMap<>();
            header.put("AccessToken", api);
            String res = HttpClientUtils.HttpsPostWithHeader(hostUrl + "/" + hostId + "/" + "accounts", body.toJSONString(), header, "utf-8");
        }
        //创建授权规则
        JSONObject body = new JSONObject();
        body.put("hostIp", ip);
        //-----查看登陆方式是什么
        body.put("hostName", hostName);
        body.put("accountName", account);
        body.put("protocol", protocol);
        String[] usernameList = new String[1];
        usernameList[0] = userNum;
        body.put("usernameList", usernameList);
        body.put("ruleName", userNum);
        Map<String, String> header = new HashMap<>();
        header.put("AccessToken", api);
        String res = HttpClientUtils.HttpsPostWithHeader(ruleUrl, body.toJSONString(), header, "utf-8");
        System.out.println("新建授权规则" + res);
    }

    public JSONObject getAccountByIp(String ip, Boolean adminFlag) {
        //获取所有主机
        int hostId = -1;
        HashMap<String, Object> headers = new HashMap<>();
        Locale alocale = Locale.US;
        DateFormat fmt = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss z", new DateFormatSymbols(alocale));
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        headers.put("Date", fmt.format(new Date()));
        headers.put("AccessToken", api);
        String hostResponse = HttpClientUtils.HttpsGetWithHeader(hostUrl + "?host=" + ip, headers);
        if (JSONObject.parseObject(hostResponse).containsKey("hosts")) {
            JSONArray hosts = JSONObject.parseObject(hostResponse).getJSONArray("hosts");
            for (int i = 0; i < hosts.size(); i++) {
                JSONObject host = hosts.getJSONObject(i);
                if (ObjectUtils.equals(host.getString("hostIp"), ip)) {
                    hostId = host.getInteger("hostId");
                    break;
                }
            }
        }
        JSONObject result = new JSONObject();
        JSONArray list = new JSONArray();
        String accountList = HttpClientUtils.HttpsGetWithHeader(hostUrl + "/" + hostId + "/" + "accounts", headers);
        if (JSONObject.parseObject(accountList).containsKey("accounts")) {
            JSONArray accounts = JSONObject.parseObject(accountList).getJSONArray("accounts");
            for (int i = 0; i < accounts.size(); i++) {
                if (!adminFlag && (accounts.getJSONObject(i).getString("accountName").equals("root") || accounts.getJSONObject(i).getString("accountName").toLowerCase().equals("administrator"))) {
                    continue;
                }
                JSONObject ac = new JSONObject();
                ac.put("accountName", accounts.getJSONObject(i).getString("accountName"));
                ac.put("accountId", accounts.getJSONObject(i).getString("accountId"));
                list.add(ac);
            }
        }
        result.put("hostId", hostId);
        result.put("mainIp", ip);
        result.put("accounts", list);
        return result;
    }

    public JSONObject getAccountPassword(int hostId, String accountId) {
        HashMap<String, Object> headers = new HashMap<>();
        Locale alocale = Locale.US;
        DateFormat fmt = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss z", new DateFormatSymbols(alocale));
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        headers.put("Date", fmt.format(new Date()));
        headers.put("AccessToken", api);
        String account = HttpClientUtils.HttpsGetWithHeader(hostUrl + "/" + hostId + "/" + "accounts" + "/" + accountId, headers);
        JSONObject passwd = new JSONObject();
        passwd.put("password", JSONObject.parseObject(account).getString("password"));
        passwd.put("accountName", JSONObject.parseObject(account).getString("accountName"));
        return passwd;
    }
}
