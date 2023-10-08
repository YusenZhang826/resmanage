package com.clic.ccdbaas.service;

import com.clic.ccdbaas.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class HiCloudService {

    private static final Logger logger = LoggerFactory.getLogger(HiCloudService.class);

    @Value("${oc.auth.url}")
    private String authUrl;
    @Value("${hicloud.auth.identity.name}")
    private String identityName;
    @Value("${hicloud.auth.identity.password}")
    private String identityPassword;
    @Value("${hicloud.url}")
    private String hiCloudUrl;
    private String identityDomainName = "中国人寿";

    public String getHiCloudToken(String scopeProjectName) {
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
                "            \"project\": {\n" +
                "                \"name\": \"%s\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", identityName, identityPassword, identityDomainName, scopeProjectName);
        token = HttpClientUtils.httpsGetMoToken(authUrl, jsonBody);
        return token;
    }

    public String getHiCloudSingleInstance(String projectId, String projectName, String nativeId) {
        String url = hiCloudUrl + projectId + "/server/detail/" + nativeId;
        String token = getHiCloudToken(projectName);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }

    public String getHiCloudInstance(String projectId, String nativeId, String token) {
        String url = hiCloudUrl + projectId + "/server/detail/" + nativeId;
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("x-auth-token", token);
        return HttpClientUtils.HttpsGetWithHeader(url, headers);
    }
}
