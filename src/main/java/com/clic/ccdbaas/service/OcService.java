package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.clic.ccdbaas.utils.RedisClientUtil.jedis;

@Service
public class OcService {
    private static final Logger logger = LoggerFactory.getLogger(OcService.class);

    @Value("${oc.auth.url}")
    private String authUrl;
    @Value("${oc.cmdb.instances.url}")
    private String cmdbInstancesUrl;
    @Value("${oc.cmdb.instances.relations.url}")
    private String cmdbRelationInstancesUrl;


    @Value("${oc.cmdb.instances.statistics.url}")
    private String cmdbInstanceStatisticsUrl;
    @Value("${oc.cmdb.instances.relations.url}")
    private String cmdbInstanceRelationUrl;
    @Value("${oc.cmdb.instances.relations.url}")
    private String relationUrl;
    @Value("${oc.cmdb.relations.class.url}")
    private String relationClassUrl;

    @Value("${oc.auth.identity.name}")
    private String identityName;
    @Value("${oc.auth.identity.password}")
    private String identityPassword;
    @Value("${oc.auth.identity.domain.name}")
    private String identityDomainName;
    @Value("${oc.auth.scope.domain.name}")
    private String scopeDomainName;
    @Value("${oc.cmdb.region}")
    private String regionId;
    @Value("${oc.cmdb.zone.capacity.url}")
    private String ocZoneCapUrl;
    @Value("${oc.cmdb.region.capacity.url}")
    private String ocRegionCapUrl;
    @Value("${oc.cmdb.azone.statistics.url}")
    private String ocZoneStatisticsUrl;
    @Value("${oc.cmdb.region.statistics.url}")
    private String ocRegionStatisticsUrl;
    @Value("${oc.cmdb.azone.storage-pool.url}")
    private String ocZoneStorageUrl;
    @Value("${oc.cmdb.region.storage-pool.url}")
    private String ocRegionStorageUrl;

    public String getOcToken() {
//        String token = jedis.get("OcToken");
//        if (token != null) {
//            return token;
//        }
        String token = "";
        String jsonBody = String.format("{\n" +
                "    \"auth\": {\n" +
                "        \"identity\": {\n" +
                "            \"methods\": [\n" +
                "                \"password\"\n" +
                "            ],\n" +
                "            \"password\": {\n" +
                "                \"user\": {\n" +
                "                    \"name\": \"%s\",\n" +
                "                    \"password\": \"%s\",\n" +
                "                    \"domain\": {\n" +
                "                        \"name\": \"%s\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        \"scope\": {\n" +
                "            \"domain\": {\n" +
                "                \"name\": \"%s\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", identityName, identityPassword, identityDomainName, scopeDomainName);
        token = HttpClientUtils.httpsGetMoToken(authUrl, jsonBody);
//        jedis.setex("OcToken", 3600, token);
        return token;
    }

    public String getOcClassInstances(String className) {
        String url = cmdbInstancesUrl + className;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    public String getOcStatistics(String className) {
        String url = cmdbInstanceStatisticsUrl.replaceAll("className", className);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    public String getOcClassInstances(String className, String instanceId) {
        String url = cmdbInstancesUrl + className + "/" + instanceId;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    /**
     * 分页获取资源实例信息
     *
     * @param className
     * @param pageNo
     * @param pageSzie
     * @return
     */
    public String getOcClassInstances(String className, int pageNo, int pageSzie) {
        String url = cmdbInstancesUrl + className + "?pageSize=" + pageSzie + "&pageNo=" + pageNo;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    /**
     * 分页获取资源实例信息(带条件)
     *
     * @param className
     * @param pageNo
     * @param pageSzie
     * @return
     */
    public String getOcClassInstancesByCondition(String className, int pageNo, int pageSzie, String condition) {
        String url = cmdbInstancesUrl + className + "?pageSize=" + pageSzie + "&pageNo=" + pageNo + "&condition=" + URLEncoder.encode(condition);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }


    public String updateOCInstances(String className, String updateJson) {
        String url = cmdbInstancesUrl + className;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.httpsPutWithAuthAndJSONRaw(url, updateJson, token);
    }


    /**
     * 数据库ip与主机ip绑定
     * source_Instance_Id:主机ip    target_Instance_Id:数据库ip  resId:数据库ip
     *
     * @return
     */
    public String bindServerAndDatabase(String relationName, String serverId, String databaseId) {
        String url = relationUrl + relationName;
        String token = getOcToken();
        String json;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[{");
        stringBuffer.append("\"" + "source_Instance_Id" + "\"" + ":\"" + serverId + "\"" + "," + "\"" + "target_Instance_Id" + "\"" + ":\"" + databaseId + "\"");
        json = stringBuffer + "}]";
        //vselect * from t_physicalserver where resId ='AABC415E3796344D9FB39CD4EB3CA009';System.out.println("jason串的值为：" + json + "url为：" + url);

        return HttpClientUtils.httpsPostWithAuthAndJSONRaw(url, json, token);
        //return  HttpClientUtils.httpsPostWithAuthAndJSONRaw("https://10.18.97.31:26335/rest/cmdb/v1/instances/relations/SYS_DATABASE_OF_SERVER","[{\"source_Instance_Id\":\"2DA8F6A83E4630509F1A4DDEE44BE104\",\"target_Instance_Id\":\"6DE11007AE643DCF96A629D51DDC8BD1\"}]",token);
    }

    /**
     * 匹配数据库与服务器
     */
    public String updateRelation(String relationName, String updateJson) {
        String url = cmdbInstanceRelationUrl + relationName;
        String token = getOcToken();
        return HttpClientUtils.httpsPostWithAuthAndJSONRaw(url, updateJson, token);


    }

    /**
     * 获取某一资源的关系
     */
    public String getRelation(String relationName, String resId) {
        String url = cmdbInstanceRelationUrl + relationName + "/" + resId;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    public String postOCInstances(String className, String postJson) {
        String url = cmdbInstanceRelationUrl + className;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.httpsPostWithAuthAndJSONRaw(url, postJson, token);
    }

    public String addInstances(String className, String postJson) {
        String url = cmdbInstancesUrl + className;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.httpsPostWithAuthAndJSONRaw(url, postJson, token);
    }

    public String getOcInstancesByCondition(String className, int pageNo, int pageSzie, String condition) {
        String url = cmdbInstancesUrl + className + "?pageSize=" + pageSzie + "&pageNo=" + pageNo + "&condition=" + URLEncoder.encode(condition);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    /**
     * 设置excel下载响应头属性
     */
    public void setExcelRespProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }

    /**
     * 获取某一关联关系下所有的的资源
     */
    public String getAllRelationRes(String relationName) {
        String url = cmdbInstanceRelationUrl + relationName;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        String relationArrJson = HttpClientUtils.HttpsGetWithHeader(url, headers);

        return relationArrJson;
    }

    /**
     * 分页获取某一关联关系下所有的映射信息
     */
    public String getPageRelation(String relationName, String condition, int pageNo, int pageSize) {

        String url = cmdbInstanceRelationUrl + relationName + "?pageSize=" + pageSize + "&pageNo=" + pageNo;
        if (!StringUtils.isEmpty(condition)) {
            url = url + "&condition=" + URLEncoder.encode(condition);
        }
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        String relationArrJson = HttpClientUtils.HttpsGetWithHeader(url, headers);

        return relationArrJson;
    }


    /**
     * 获取所有的关联关系信息
     *
     * @param relationName
     * @return
     */
    public JSONArray getAllRelationInfo(String relationName) {
        String message = "";
        JSONObject jsob = null;
        JSONArray hostArr = new JSONArray();
        try {

            int pageNo = 1;
            int pageSize = 1000;
            message = getPageRelation(relationName, "", pageNo, pageSize);

            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");
            while ((pageNo - 1) * pageSize < totalNum) {
                String tmpMessage = getPageRelation(relationName, "", pageNo, pageSize);
                JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                JSONArray tmpHostArr = tmpJsonInfo.getJSONArray("objList");
                hostArr.addAll(tmpHostArr);
//                logger.info("获取" + relationName + "信息：第" + pageNo + "页，返回数据为-------" + tmpMessage);
                pageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostArr;
    }

    public JSONArray getAllRelationWithCondition(String relationName, String condition) {
        String message = "";
        JSONObject jsob = null;
        JSONArray hostArr = new JSONArray();
        try {

            int pageNo = 1;
            int pageSzie = 1000;
            message = getPageRelation(relationName, condition, pageNo, pageSzie);

            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");
            while ((pageNo - 1) * pageSzie < totalNum) {
                String tmpMessage = getPageRelation(relationName, condition, pageNo, pageSzie);
                JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                JSONArray tmpHostArr = tmpJsonInfo.getJSONArray("objList");
                hostArr.addAll(tmpHostArr);
                logger.info("获取" + relationName + "信息：第" + pageNo + "页，返回数据为-------" + tmpMessage);
                pageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostArr;
    }


    public JSONArray getAllInstanceInfo(String className) {
        String message = "";
        JSONObject jsob = null;
        JSONArray hostArr = new JSONArray();
        try {
            int pageNo = 1;
            int pageSize = 1000;
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

            message = getOcClassInstances(className, pageNo, pageSize);
            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");
            //   Map pageNoMap = new HashMap();
            for (int i = 1; (i - 1) * pageSize < totalNum; i++) {
                int j = i;
                executor.submit(() -> {
                    String tmpMessage = getOcClassInstances(className, j, pageSize);
                    JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                    JSONArray tmpHostArr = tmpJsonInfo.getJSONArray("objList");
                    hostArr.addAll(tmpHostArr);
                    //     logger.info("获取" + className + "信息：第" + j + "页，返回数据为-------" + tmpMessage);
                });
            }
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostArr;
    }

    public JSONArray getAllInstanceWithCondition(String className, String condition) {
        String message = "";
        JSONObject jsob = null;
        JSONArray hostArr = new JSONArray();
        try {
            int pageNo = 1;
            int pageSize = 1000;
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

            message = getOcInstancesByCondition(className, pageNo, pageSize, condition);
//            message = getOcClassInstances(className, pageNo, pageSize);
            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");
            //   Map pageNoMap = new HashMap();
            for (int i = 1; (i - 1) * pageSize < totalNum; i++) {
                int j = i;
                executor.submit(() -> {
                    String tmpMessage = getOcInstancesByCondition(className, j, pageSize, condition);
                    JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                    JSONArray tmpHostArr = tmpJsonInfo.getJSONArray("objList");
                    hostArr.addAll(tmpHostArr);
                    logger.info("获取" + className + "信息：第" + j + "页，返回数据为-------" + tmpMessage);
                });
            }
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostArr;
    }


    //通过唯一ID查询具体信息
    public JSONObject getAllInstanceInfo(String className, String instanceId) {
        String message = "";
        JSONObject jsob = null;
        try {
            message = getOcClassInstances(className, instanceId);
            jsob = JSONObject.parseObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsob;
    }


    /**
     * 条件查询  需要将三个模糊查询条件合并并且跟其他条件做交集
     *
     * @param requestParams
     * @param className
     * @return
     */

    public JSONArray AllInstanceInfoByCondition(JSONObject requestParams, String className) {
        //查询状态、产品、团队运行结果
        String paramValue = requestParams.getString("paramValue");

        JSONArray andConditonArr = getAllInstanceByAnd(requestParams, className);
        //ip、name、管理员三个 满足一个即可
        //两个结果取并集
        requestParams.put("paramValue", paramValue);
        JSONArray orConditonArr = getAllInstanceByOr(requestParams, className);

        List resInstanceList = andConditonArr.stream().filter(orConditonArr::contains).collect(Collectors.toList());


        return new JSONArray(resInstanceList);
    }

    public String getRelationInstances(String relationName, String token, int pageNo, int pageSzie) {
        String url = cmdbRelationInstancesUrl + relationName + "?pageSize=" + pageSzie + "&pageNo=" + pageNo;
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    public String getRelationInstancesByCondition(String relationName, int pageNo, int pageSize, String condition) {
        String url = cmdbRelationInstancesUrl + relationName + "?pageSize=" + pageSize + "&pageNo=" + pageNo + "&condition=" + URLEncoder.encode(condition);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    public String getStorageInstanceByCondition(String storageName, int pageNo, int pageSize, String condition) {
        String url = cmdbInstancesUrl + storageName + "?pageSize=" + pageSize + "&pageNo=" + pageNo + "&condition=" + URLEncoder.encode(condition);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }


    //条件查询
    public JSONArray getAllInstanceByAnd(JSONObject requestParams, String className) {
        String message = "";
        String condition = "";
        JSONObject jsob = null;
        JSONArray hostArr = new JSONArray();
        try {
            requestParams.remove("paramValue");
            condition = getParams(requestParams);
            logger.info("codition:" + condition);
            int pageNo = 1;
            int pageSzie = 1000;
            message = getOcInstancesByCondition(className, pageNo, pageSzie, condition);
            logger.info("resInfo:" + message);
            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");
            while ((pageNo - 1) * pageSzie < totalNum) {
                String tmpMessage = getOcInstancesByCondition(className, pageNo, pageSzie, condition);
                JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                JSONArray tmpHostArr = tmpJsonInfo.getJSONArray("objList");
                hostArr.addAll(tmpHostArr);
                logger.info("获取" + className + "信息：第" + pageNo + "页，返回数据为-------" + tmpMessage);
                pageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostArr;
    }

    //条件查询
    public JSONArray getAllInstanceByOr(JSONObject requestParams, String className) {
        String message = "";
        String condition = "";
        JSONObject jsob = null;
        JSONArray hostArr = new JSONArray();
        try {
            requestParams.remove("belongProduct");
            requestParams.remove("sysAdministrator");
            requestParams.remove("status");
            condition = getParams(requestParams);
            int pageNo = 1;
            int pageSzie = 1000;
            message = getOcInstancesByCondition(className, pageNo, pageSzie, condition);

            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");
            while ((pageNo - 1) * pageSzie < totalNum) {
                String tmpMessage = getOcInstancesByCondition(className, pageNo, pageSzie, condition);
                JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                JSONArray tmpHostArr = tmpJsonInfo.getJSONArray("objList");
                hostArr.addAll(tmpHostArr);
                logger.info("获取" + className + "信息：第" + pageNo + "页，返回数据为-------" + tmpMessage);
                pageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostArr;
    }


    //获取请求body
    public String getParams(JSONObject requestParams) {
        Map condition = new HashMap<>();
        List conditonList = new ArrayList();
        if (!StringUtils.isEmpty(requestParams.getString("paramValue"))) {
            conditonList.add(getConbimeCondition("name", requestParams.getString("paramValue"), "contain", "or"));
        }

        if (!StringUtils.isEmpty(requestParams.getString("paramValue"))) {
            conditonList.add(getConbimeCondition("sysAdministrator", requestParams.getString("paramValue"), "contain", "or"));

        }

        if (!StringUtils.isEmpty(requestParams.getString("paramValue"))) {
            conditonList.add(getConbimeCondition("ipAddress", requestParams.getString("paramValue"), "contain", "or"));

        }
        if (!StringUtils.isEmpty(requestParams.getString("belongProduct"))) {
            conditonList.add(getConbimeCondition("belongProduct", requestParams.getString("belongProduct"), "contain", "and"));

        }

        if (!StringUtils.isEmpty(requestParams.getString("status"))) {
            conditonList.add(getConbimeCondition("status", requestParams.getString("status"), "equal", "and"));

        }
        if (!StringUtils.isEmpty(requestParams.getString("name"))) {
            conditonList.add(getConbimeCondition("name", requestParams.getString("name"), "contain", "and"));
        }
        if (!StringUtils.isEmpty(requestParams.getString("sysAdminTeam"))) {
            conditonList.add(getConbimeCondition("status", requestParams.getString("status"), "contain", "and"));
        }
        if (!StringUtils.isEmpty(requestParams.getString("nativeId"))) {
            conditonList.add(getConbimeCondition("nativeId", requestParams.getString("nativeId"), "contain", "and"));

        }
        if (!StringUtils.isEmpty(requestParams.getString("azoneId"))) {
            conditonList.add(getConbimeCondition("azoneId", requestParams.getString("azoneId"), "equal", "and"));

        }
        condition.put("constraint", conditonList);
        String result = JSONObject.toJSON(condition).toString();
        return result;
    }

    /**
     * 拼接查询条件
     *
     * @param requestParams
     * @return
     */
    public String dealParams(JSONObject requestParams) {
        Map condition = new HashMap<>();
        List conditonList = new ArrayList();
        for (Map.Entry<String, Object> entry : requestParams.entrySet()) {
            String key = entry.getKey();

            //页码条件不拼接  其他拼接使用contain连接串
            if ("pageNum".equals(key) || "pageSize".equals(key) || "className".equals(key)) {
                continue;
            } else if ("status".equals(key)) {
                String value = (String) entry.getValue();
                String[] statusArr = value.split(",");
                conditonList.add(getConbimeCondition(key, statusArr, "in", "and"));
                System.out.println(entry.getKey() + " : " + entry.getValue());
            } else {
                String value = (String) entry.getValue();
                conditonList.add(getConbimeCondition(key, value, "contain", "and"));
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

        }
        condition.put("constraint", conditonList);
        String result = JSONObject.toJSON(condition).toString();
        return result;
    }

    /**
     * 拼接in类型查询条件
     *
     * @param idList
     * @return
     */
    public String dealInParams(List<String> idList) {
        Map condition = new HashMap<>();
        List conditonList = new ArrayList();
        conditonList.add(getConbimeCondition("volumeId", idList, "in", "and"));


        // condition.put("constraint", conditonList);
        String result = JSONObject.toJSON(conditonList).toString();
        return result;
    }

    /**
     * 拼接in类型查询条件
     *
     * @param idList
     * @return
     */
    public String dealInstanceInParams(List<String> idList) {
        Map condition = new HashMap<>();
        List conditonList = new ArrayList();
        conditonList.add(getConbimeCondition("resId", idList, "in", "and"));


        condition.put("constraint", conditonList);
        String result = JSONObject.toJSON(condition).toString();
        return result;
    }


    //拼接查询条件
    public Map getConbimeCondition(String key, Object value, String operator, String linkOperator) {
        if (value != null) {
            logger.info("查询ECS条件为：" + "name:" + key + "     value:" + value);
        }

        Map simple = new HashMap<>();
        Map<String, Object> simpleValue = new HashMap<>();
        simpleValue.put("name", key);
        simpleValue.put("value", value);
        simpleValue.put("operator", operator);
        simple.put("simple", simpleValue);
        simple.put("logOp", linkOperator);
        return simple;

    }


    //拼接查询条件
    public String getCondition(String key, String value, String operator, String linkOperator) {
        if (value != null) {
            logger.info("查询ECS条件为：" + "name:" + key + "     value:" + value);
        }
        Map condition = new HashMap<>();
        List<Map<String, Map<String, String>>> constraint = new ArrayList<>();
        Map simple = new HashMap<>();
        Map<String, String> simpleValue = new HashMap<>();
        simpleValue.put("name", key);
        simpleValue.put("value", value);
        simpleValue.put("operator", operator);
        simple.put("simple", simpleValue);
        simple.put("logOp", linkOperator);
        constraint.add(simple);
        condition.put("constraint", constraint);
        String result = JSONObject.toJSON(condition).toString();
        return result;
    }

    public String getStorageCondition(String key, List<String> value, String operator, String linkOperator) {
        if (value != null) {
            logger.info("查询ECS条件为：" + "name:" + key + "     value:" + value);
        }
        Map condition = new HashMap<>();
        List<Map<String, Map<String, String>>> constraint = new ArrayList<>();
        Map simple = new HashMap<>();
        Map<String, Object> simpleValue = new HashMap<>();
        simpleValue.put("name", key);
        simpleValue.put("value", value);
        simpleValue.put("operator", operator);
        simple.put("simple", simpleValue);
//        simple.put("logOp", linkOperator);
        constraint.add(simple);
        condition.put("constraint", constraint);
        String result = JSONObject.toJSON(condition).toString();
        return result;
    }

    public String getConditionWithMultiParams(List<Map<String, Object>> conditionParams) {
        if (conditionParams.size() == 0) return "";

        Map condition = new HashMap<>();
        List<Map<String, Map<String, String>>> constraint = new ArrayList<>();
        for (Map<String, Object> entry : conditionParams) {
            Map simple = new HashMap<>();
            if (entry.containsKey("logOp")) simple.put("logOp", entry.get("logOp"));
            Map<String, Object> simpleValue = new HashMap<>();
            simpleValue.put("name", entry.get("name"));
            simpleValue.put("value", entry.get("value"));
            simpleValue.put("operator", entry.get("operator"));
            simple.put("simple", simpleValue);
            constraint.add(simple);
        }
        condition.put("constraint", constraint);
        String result = JSONObject.toJSON(condition).toString();
        return result;
    }

    //转译成分区名称
    public String getRegion(String value, JSONObject requestParams) {
        String region = jedis.get(value);
        if (region != null) {
            return region;
        }
        Map<String, String> regionIds = getRegionIds();
        region = regionIds.get(requestParams.getString("region"));
        jedis.setex(value, 3600, region);
        return region;
    }

    //获取分区Ids
    public Map<String, String> getRegionIds() {
        String ocClassInstances = getOcClassInstances(regionId);
        JSONArray regionIds = JSONObject.parseObject(ocClassInstances).getJSONArray("objList");
        Map<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < regionIds.size(); i++) {
            String region = regionIds.getJSONObject(i).getString("globalId");
            String name = regionIds.getJSONObject(i).getString("name");
            result.put(region, name);
        }
        return result;
    }

    /**
     * 根据region获取cpu 内存等
     *
     * @param regionId
     * @return
     */
    public JSONObject getOcRegionCapInfo(String regionId) {
        String url = ocRegionCapUrl.replaceAll("\\{regionId}", regionId);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        String resJson = HttpClientUtils.HttpsGetWithHeader(url, headers);
        return JSON.parseObject(resJson);
    }

    /**
     * 根据zone获取cpu 内存等
     *
     * @param zoneId
     * @return
     */
    public JSONObject getOcZoneCapInfo(String zoneId) {
        String url = ocZoneCapUrl.replaceAll("\\{azoneId}", zoneId);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        String resJson = HttpClientUtils.HttpsGetWithHeader(url, headers);
        return JSON.parseObject(resJson);
    }

    /**
     * 根据region获取个数信息
     *
     * @param regionId
     * @return
     */
    public JSONObject getOcRegionStatisticsInfo(String regionId) {
        String url = ocRegionStatisticsUrl.replaceAll("\\{regionId}", regionId);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        String resJson = HttpClientUtils.HttpsGetWithHeader(url, headers);
        return JSON.parseObject(resJson);
    }

    /**
     * 根据zone获取个数信息
     *
     * @param zoneId
     * @return
     */
    public JSONObject getOcZoneStatisticsInfo(String zoneId) {
        String url = ocZoneStatisticsUrl.replaceAll("\\{azoneId}", zoneId);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        String resJson = HttpClientUtils.HttpsGetWithHeader(url, headers);
        return JSON.parseObject(resJson);
    }

    /**
     * 根据region获取容量信息
     *
     * @param regionId
     * @return
     */
    public JSONObject getOcRegionStorageInfo(String regionId) {
        String url = ocRegionStorageUrl.replaceAll("\\{regionId}", regionId);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        String resJson = HttpClientUtils.HttpsGetWithHeader(url, headers);
        return JSON.parseObject(resJson);
    }

    /**
     * 根据zone获取容量信息
     *
     * @param zoneId
     * @return
     */
    public JSONObject getOcZoneStorageInfo(String zoneId) {
        String url = ocZoneStorageUrl.replaceAll("\\{azoneId}", zoneId);
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        String resJson = HttpClientUtils.HttpsGetWithHeader(url, headers);
        return JSON.parseObject(resJson);
    }

    public String deleteOcClassInstances(String className, String instanceId) {
        String url = cmdbInstancesUrl + className + "/" + instanceId;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.httpsDeleteWithAJSONRaw(url, token);
    }


    public String getOcClassInstancesByPageSize(String className, int PageSize) {
        String url = cmdbInstancesUrl + className + "?" + "pageSize=" + PageSize;
        String token = getOcToken();
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    /**
     * 获取指定资源类型的关联关系
     *
     * @param className
     * @param pageNum
     * @return
     */
    public String getRelationClass(String className, int pageNum) {
        String url = relationClassUrl + className + "/relations?includeSource=true&includeTarget=true&pageSize=1000&pageNum=" + pageNum;
        String token = getOcToken();
        Map<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    /**
     * 获取指定资源类型的全量关联关系
     *
     * @param className
     * @return
     */
    public JSONArray getAllRelationClass(String className) {
        String message = "";
        JSONObject jsob = null;
        JSONArray hostArr = new JSONArray();
        try {

            int pageNo = 1;
            int pageSize = 1000;
            message = getRelationClass(className, pageNo);
            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");

            while ((pageNo - 1) * pageSize < totalNum) {
                String tmpMessage = getRelationClass(className, pageNo);
                JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                JSONArray tmpHostArr = tmpJsonInfo.getJSONArray("objList");
                hostArr.addAll(tmpHostArr);
                logger.info("获取" + className + "关联关系信息：第" + pageNo + "页，返回数据为-------" + tmpMessage);
                pageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostArr;
    }

    /**
     * 获取资源类型数据
     *
     * @return
     */
    public String getOCResourceTypeInfo(int pageNum) {
        String url = relationClassUrl.substring(0, relationClassUrl.length() - 1) + "?categoryKey=PhysicalDevice&pageSize=100&pageNo=" + pageNum;
        String token = getOcToken();
        Map<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    /**
     * 获取全量资源类型数据
     *
     * @return
     */
    public JSONArray getAllResourceTypeInfo() {
        String message = "";
        JSONObject jsob = null;
        JSONArray hostArr = new JSONArray();
        try {
            int pageNo = 1;
            int pageSize = 100;
            message = getOCResourceTypeInfo(pageNo);
            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");

            while ((pageNo - 1) * pageSize < totalNum) {
                String tmpMessage = getOCResourceTypeInfo(pageNo);
                JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                JSONArray tmpHostArr = tmpJsonInfo.getJSONArray("objList");
                hostArr.addAll(tmpHostArr);
                logger.info("获取资源类型信息：第" + pageNo + "页，返回数据为-------" + tmpMessage);
                pageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostArr;
    }
}
