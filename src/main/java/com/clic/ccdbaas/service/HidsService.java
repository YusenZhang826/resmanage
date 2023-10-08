package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.utils.HttpClientUtils;
import com.clic.ccdbaas.utils.Sha1Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/5/22 15:47
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class HidsService {
    @Value("${hids.north.gettoken.url}")
    private String tokenUrl;

    @Value("${hids.north.winhostinfo.url}")
    private String winHostInfoUrl;

    @Value("${hids.north.linuxhostinfo.url}")
    private String linuxHostInfoUrl;

    @Value("${hids.north.process.url}")
    private String hidsProcessInfoUrl;

    @Value("${hids.north.port.url}")
    private String hidsPortInfoUrl;


    @Value("${hids.north.appinfo.url}")
    private String hidsAppInfoUrl;

    @Value("${hids.north.username}")
    private String userName;

    @Value("${hids.north.password}")
    private String password;


    public JSONArray getAllHidsHostArr() {
        String hidsHostLinuxInfo = getHostInfo(0, 300000, tokenUrl, linuxHostInfoUrl, userName, password);
        String hidsHostWindowInfo = getHostInfo(0, 200000, tokenUrl, winHostInfoUrl, userName, password);
//        String ghidsHostLinuxInfo = getHostInfo(0, 200000, gHidsTokenUrl, ghisdLinuxHostInfoUrl, gHidsUserName, gHidsPassword);
//        String ghidsHostWindowInfo = getHostInfo(0, 200000, gHidsTokenUrl, gHidsWinHostInfoUrl, gHidsUserName, gHidsPassword);
        JSONArray totalHostArr = new JSONArray();
        JSONArray hidsLinuxArr = JSON.parseObject(hidsHostLinuxInfo).getJSONArray("rows");
        JSONArray hidsWindowArr = JSON.parseObject(hidsHostWindowInfo).getJSONArray("rows");
//        JSONArray ghidsLinuxArr = JSON.parseObject(ghidsHostLinuxInfo).getJSONArray("rows");
//        JSONArray ghidsWindowArr = JSON.parseObject(ghidsHostWindowInfo).getJSONArray("rows");
        totalHostArr.addAll(hidsLinuxArr);
        totalHostArr.addAll(hidsWindowArr);
//        totalHostArr.addAll(ghidsLinuxArr);
//        totalHostArr.addAll(ghidsWindowArr);

        return totalHostArr;
    }


    /**
     * 根据进程信息补充数据库信息
     *
     * @return
     */
    public JSONArray getAllHidsProcess() {
        JSONArray totalHostArr = new JSONArray();
        TreeMap<String, Object> dmParams = new TreeMap<>();
        dmParams.put("name", "polar-postgres");
        String hidsLinuxAppInfo = getProcessInfo(dmParams, "linux", hidsProcessInfoUrl, tokenUrl, userName, password);
//        String ghidsLinuxAppInfo = getProcessInfo(dmParams, "linux", ghidsProcessInfoUrl, gHidsTokenUrl, gHidsUserName, gHidsPassword);
        JSONArray hidsLinuxArr = JSON.parseObject(hidsLinuxAppInfo).getJSONArray("rows");
//        JSONArray ghidsLinuxArr = JSON.parseObject(ghidsLinuxAppInfo).getJSONArray("rows");
        totalHostArr.addAll(hidsLinuxArr);
//        totalHostArr.addAll(ghidsLinuxArr);

        TreeMap<String, Object> hanaParams = new TreeMap<>();
        hanaParams.put("name", "hdbindexserver");
        String hidsHanaAppInfo = getProcessInfo(hanaParams, "linux", hidsProcessInfoUrl, tokenUrl, userName, password);
//        String ghidsHanaAppInfo = getProcessInfo(hanaParams, "linux", ghidsProcessInfoUrl, gHidsTokenUrl, gHidsUserName, gHidsPassword);
        JSONArray hidsHanaArr = JSON.parseObject(hidsHanaAppInfo).getJSONArray("rows");
//        JSONArray ghidsHanaArr = JSON.parseObject(ghidsHanaAppInfo).getJSONArray("rows");
        totalHostArr.addAll(hidsHanaArr);
//        totalHostArr.addAll(ghidsHanaArr);

        return totalHostArr;
    }


    /**
     * 根据进程信息补充数据库信息
     *
     * @return
     */
    public JSONArray getAllHidsPort() {
        /**
         * 获取达梦数据库信息
         */
        JSONArray totalHostArr = new JSONArray();
        TreeMap<String, Object> dmServerParams = new TreeMap<>();
        dmServerParams.put("processName", "dmserver");
        String hidsLinuxPortInfo = getResInfoByUrl(dmServerParams, "linux", hidsPortInfoUrl, tokenUrl, userName, password);
//        String gHidsLinuxPortInfo = getResInfoByUrl(dmServerParams, "linux", gHidsPortInfoUrl, gHidsTokenUrl, gHidsUserName, gHidsPassword);
        JSONArray hidsLinuxArr = JSON.parseObject(hidsLinuxPortInfo).getJSONArray("rows");
//        JSONArray gHidsLinuxArr = JSON.parseObject(gHidsLinuxPortInfo).getJSONArray("rows");
        totalHostArr.addAll(hidsLinuxArr);
//        totalHostArr.addAll(gHidsLinuxArr);
        /*
        获取hana数据库信息
         */
        TreeMap<String, Object> hanaServerParams = new TreeMap<>();
        hanaServerParams.put("processName", "hdbindexserver");
        String hidsHanaPortInfo = getResInfoByUrl(hanaServerParams, "linux", hidsPortInfoUrl, tokenUrl, userName, password);
//        String gHidsHanaPortInfo = getResInfoByUrl(hanaServerParams, "linux", gHidsPortInfoUrl, gHidsTokenUrl, gHidsUserName, gHidsPassword);
        JSONArray hidsHanaLinuxArr = JSON.parseObject(hidsHanaPortInfo).getJSONArray("rows");
//        JSONArray gHidsHanaLinuxArr = JSON.parseObject(gHidsHanaPortInfo).getJSONArray("rows");
        totalHostArr.addAll(hidsHanaLinuxArr);
//        totalHostArr.addAll(gHidsHanaLinuxArr);


        return totalHostArr;
    }


    /**
     * 获取所有中间件信息
     *
     * @return
     */
    public JSONArray getAllAppInfo() {
        /**
         * 获取达梦数据库信息
         */
        JSONArray totalAppArr = new JSONArray();
        TreeMap<String, Object> dmServerParams = new TreeMap<>();
      /*  dmServerParams.put("ip","10.18.11.186");
        dmServerParams.put("name","Nginx");*/
        String hidsLinuxAppInfo = getResInfoByUrl(dmServerParams, "linux", hidsAppInfoUrl, tokenUrl, userName, password);
//        String gHidsLinuxAppInfo = getResInfoByUrl(dmServerParams, "linux", ghidsAppInfoUrl, gHidsTokenUrl, gHidsUserName, gHidsPassword);
        String hidsWinAppInfo = getResInfoByUrl(dmServerParams, "win", hidsAppInfoUrl, tokenUrl, userName, password);
//        String gHidsWinAppInfo = getResInfoByUrl(dmServerParams, "win", ghidsAppInfoUrl, gHidsTokenUrl, gHidsUserName, gHidsPassword);
        JSONArray hidsLinuxArr = JSON.parseObject(hidsLinuxAppInfo).getJSONArray("rows");
//        JSONArray gHidsLinuxArr = JSON.parseObject(gHidsLinuxAppInfo).getJSONArray("rows");
        JSONArray hidsWinArr = JSON.parseObject(hidsWinAppInfo).getJSONArray("rows");
//        JSONArray gHidsWinArr = JSON.parseObject(gHidsWinAppInfo).getJSONArray("rows");
        totalAppArr.addAll(hidsLinuxArr);
//        totalAppArr.addAll(gHidsLinuxArr);
        totalAppArr.addAll(hidsWinArr);
//        totalAppArr.addAll(gHidsWinArr);
        return totalAppArr;
    }

    public String getHostInfo(int page, int size, String tokenUrl, String linuxHostInfoUrl, String userName, String password) {
        TreeMap<String, Object> params = new TreeMap<>();
        params.put("page", String.valueOf(page));
        params.put("size", String.valueOf(size));
        params.put("userName", userName);
        //params.put("agentId","cab1b1ccfe0a08c7");
        params.put("password", String.valueOf(password));
        params.put("tokenUrl", String.valueOf(tokenUrl));
        Map<String, Object> headers = getHidsGetHeaders(params);
        String hostInfo;

        if (linuxHostInfoUrl.startsWith("https")) {
            hostInfo = HttpClientUtils.httpsGetRequest(linuxHostInfoUrl, headers, params);
        } else {
            hostInfo = HttpClientUtils.httpGetRequest(linuxHostInfoUrl, headers, params);

        }

        return hostInfo;
    }


    /**
     * 获取相关进程信息
     *
     * @param params
     * @param hostType
     * @param tokenUrl
     * @param userName
     * @param password
     * @return
     */
    public String getProcessInfo(TreeMap<String, Object> params, String hostType, String requestUrl, String tokenUrl, String userName, String password) {
        params.put("page", "0");
        params.put("size", "100000");
        params.put("userName", userName);
        params.put("password", String.valueOf(password));
        params.put("tokenUrl", String.valueOf(tokenUrl));
        Map<String, Object> headers = getHidsGetHeaders(params);
        String appInfo;
        requestUrl = requestUrl.replaceAll("\\{hostType}", hostType);
        if (requestUrl.startsWith("https")) {
            appInfo = HttpClientUtils.httpsGetRequest(requestUrl, headers, params);
        } else {
            appInfo = HttpClientUtils.httpGetRequest(requestUrl, headers, params);
        }

        return appInfo;
    }


    public Map<String, Object> getHidsGetHeaders(TreeMap<String, Object> params) {
        String tokenUrl = (String) params.get("tokenUrl");
        String userName = (String) params.get("userName");
        String password = (String) params.get("password");
        String tokenBody = getHidsToken(tokenUrl, userName, password);
        JSONObject tokenJson = JSON.parseObject(tokenBody).getJSONObject("data");
        String signKey = tokenJson.getString("signKey");
        String jwt = tokenJson.getString("jwt");
        String comId = tokenJson.getString("comId");
        int ts = (int) (System.currentTimeMillis() / 1000.0);

        StringBuilder paramsInfo = new StringBuilder();
        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            paramsInfo.append(entry.getKey());
            paramsInfo.append(entry.getValue());
        }
        String sign = Sha1Util.getSha1(comId + paramsInfo + ts + signKey);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + jwt);
        headers.put("comId", comId);
        headers.put("sign", sign);
        headers.put("timestamp", String.valueOf(ts));
        headers.put("Content-Type", "application/json");

        return headers;
    }


    /**
     * 获取相关进程信息
     *
     * @param params
     * @param hostType
     * @param tokenUrl
     * @param userName
     * @param password
     * @return
     */
    public String getResInfoByUrl(TreeMap<String, Object> params, String hostType, String portUrl, String tokenUrl, String userName, String password) {
        params.put("page", "0");
        params.put("size", "300000");
        params.put("userName", userName);
        params.put("password", String.valueOf(password));
        params.put("tokenUrl", String.valueOf(tokenUrl));
        Map<String, Object> headers = getHidsGetHeaders(params);
        String appInfo;
        portUrl = portUrl.replaceAll("\\{hostType}", hostType);
        if (portUrl.startsWith("https")) {
            appInfo = HttpClientUtils.httpsGetRequest(portUrl, headers, params);
        } else {
            appInfo = HttpClientUtils.httpGetRequest(portUrl, headers, params);
        }

        return appInfo;
    }


    public String getHidsToken(String url, String userName, String password) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", password);
        String tokenBody = "";
        try {
            if (url.startsWith("https")) {
                tokenBody = HttpClientUtils.httpsPostRequest(url, params);

            } else {
                tokenBody = HttpClientUtils.httpPostRequest(url, params);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tokenBody;
    }


}
