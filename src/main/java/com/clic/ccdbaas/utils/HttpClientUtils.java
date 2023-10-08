package com.clic.ccdbaas.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * @author taoliliang 2019-05
 */
public class HttpClientUtils {

    private static PoolingHttpClientConnectionManager connectionManager;
    private static final String EMPTY_STR = "";
    private static final String UTF_8 = "UTF-8";
    private static final Random random = new Random();
    private static String[] userAgents = null;

    private static void init() {
        if (connectionManager == null) {
            connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(50);// 整个连接池最大连接数
            connectionManager.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(connectionManager).setRetryHandler(new DefaultHttpRequestRetryHandler(1, true)).build();
    }

    /**
     * 获取http模拟请求User-Agent信息
     *
     * @param n
     * @return
     */
    public static String getUserAgent(int n) {
        if (userAgents == null) {
            userAgents = new String[24];
            userAgents[0] = "Mozilla/5.0 (compatible; EasouSpider; +http://www.easou.com/search/spider.html)"; // easou
            userAgents[1] = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Safari/537.36";
            userAgents[2] = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; MDDR; .NET4.0C; .NET4.0E; .NET CLR 1.1.4322; Tablet PC 2.0)"; // 360spider
            userAgents[3] = "Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)";
            userAgents[4] = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.1)";
            userAgents[5] = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.43 BIDUBrowser/2.x Safari/537.31";
            userAgents[6] = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Tablet PC 2.0)";
            userAgents[7] = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1";
            userAgents[8] = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; SV1; .NET CLR 1.0.3705; .NET CLR 2.0.50727; .NET CLR 1.1.4322)";
            userAgents[9] = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648)";
            userAgents[10] = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET4.0C; .NET4.0E)";
            userAgents[11] = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; EasyBits GO v1.0; InfoPath.1; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)";
            userAgents[12] = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1";
            userAgents[13] = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0)";
            userAgents[14] = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/7.0; MASP)";
            userAgents[15] = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.802.30 Safari/535.1 SE 2.X MetaSr 1.0";
            userAgents[16] = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.79 Safari/535.11 QIHU THEWORLD";
            userAgents[17] = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36";
            userAgents[18] = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31";
            userAgents[19] = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; )  Firefox/1.5.0.11; 360Spider";
            userAgents[20] = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36";
            userAgents[21] = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/3.5 Safari/536.11";
            userAgents[22] = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0 AlexaToolbar/alxf-2.19";
            userAgents[23] = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        }
        if (n > userAgents.length) {
            n = 0;
        }
        return userAgents[n];
    }

    public static String HttpsGetMoWithHeader(String url, Map<String, Object> headers) {
        HttpGet httpGet = new HttpGet(url);
        //StringEntity entity = new StringEntity(json, "utf-8");
        //httpGet.setEntity(entity);
        httpGet.setHeader("Content-type", "application/json;charset=UTF-8");
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        String respContent = null;
        HttpResponse resp = null;
        HttpClient client = null;
        try {
            client = new SSLClient();
            resp = client.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return respContent;
    }

    public static String httpsPutWithAuthAndJSONRaw(String url, String json, String token) {
        HttpPut httpPut = new HttpPut(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        httpPut.setEntity(entity);
        httpPut.setHeader("Content-type", "application/json");
        httpPut.setHeader("X-Auth-Token", token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            HttpClient client = new SSLClient();
            resp = client.execute(httpPut);
            System.out.println("resp:" + resp);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
                System.out.println("respContent:" + respContent);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return respContent;
    }


    public static String httpsPostWithAuthInfo(String url, String json, String token) {
        HttpPost httpPut = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        httpPut.setEntity(entity);
        httpPut.setHeader("Content-type", "application/json");
        httpPut.setHeader("Authorization", token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            HttpClient client = new SSLClient();
            resp = client.execute(httpPut);
            System.out.println("resp:" + resp);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
                System.out.println("respContent:" + respContent);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return respContent;
    }

    public static String httpsPostWithAuthAndJSONRaw(String url, String json, String token) {
        HttpPost httpPut = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        httpPut.setEntity(entity);
        httpPut.setHeader("Content-type", "application/json");
        httpPut.setHeader("X-Auth-Token", token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            HttpClient client = new SSLClient();
            resp = client.execute(httpPut);
            System.out.println("resp:" + resp);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
                System.out.println("respContent:" + respContent);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return respContent;
    }

    /****对接MO**oc   by  taoliliang  **/

    public static String httpsPutMoToken(String url, String json) {
        HttpPut httpPut = new HttpPut(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        httpPut.setEntity(entity);
        httpPut.setHeader("Content-type", "application/json");
        String respContent = null;
        HttpResponse resp = null;
        HttpClient client = null;
        try {
            client = new SSLClient();
            resp = client.execute(httpPut);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return respContent;
    }

    /****对接MO**   by  wangting  **/

    public static String httpsGetMoToken(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");
        String token = null;
        HttpResponse resp = null;
        HttpClient client = null;
        try {
            client = new SSLClient();
            resp = client.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                token = resp.getFirstHeader("X-Subject-Token").getValue();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return token;
    }

    /**
     * https get
     *
     * @param
     * @return
     */
    public static String HttpsGetWithHeader(String url, Map<String, Object> headers) {
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/json");
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }
            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 测试https post
     *
     * @param
     * @return
     */
    public static String HttpsPostWithJson(String url, String jsonstr, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            System.setProperty("jsse.enableSNIExtension", "false");
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            StringEntity se = new StringEntity(jsonstr);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 测试https get
     *
     * @param
     * @return
     */
    public static String HttpsGet(String url, String charset) {
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);

            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String httpPostWithAuthAndJSONRaw(String url, String json, String username, String password) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");
        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        // System.out.println(token);
        httpPost.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            resp = client.execute(httpPost);
            //if (resp.getStatusLine().getStatusCode() == 200 || resp.getStatusLine().getStatusCode() == 201) {
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            /*
             * if(resp.getStatusLine().getStatusCode() == 200) { HttpEntity he =
             * resp.getEntity(); respContent = EntityUtils.toString(he,"UTF-8");
             * }
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static String httpPutWithAuthAndJSONRaw(String url, String json, String username, String password) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        httpPut.setEntity(entity);
        httpPut.setHeader("Content-type", "application/json");
        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        // System.out.println(token);
        httpPut.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            resp = client.execute(httpPut);
            if (resp.getStatusLine().getStatusCode() == 200 || resp.getStatusLine().getStatusCode() == 201) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            /*
             * if(resp.getStatusLine().getStatusCode() == 200) { HttpEntity he =
             * resp.getEntity(); respContent = EntityUtils.toString(he,"UTF-8");
             * }
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static String httpDeleteWithAuthAndJSONRaw(String url, String json, String username, String password) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
//		StringEntity entity = new StringEntity(json, "utf-8");
//		httpDelete.setEntity(entity);
        httpDelete.setHeader("Content-type", "application/json");
        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        // System.out.println(token);
        httpDelete.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            resp = client.execute(httpDelete);
            if (resp.getStatusLine().getStatusCode() == 200 || resp.getStatusLine().getStatusCode() == 201) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            /*
             * if(resp.getStatusLine().getStatusCode() == 200) { HttpEntity he =
             * resp.getEntity(); respContent = EntityUtils.toString(he,"UTF-8");
             * }
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static String httpsPostWithAuth(String url, String username, String password) throws UnsupportedEncodingException {
        HttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);

        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        httpPost.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            httpClient = new SSLClient();
            resp = httpClient.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static String httpPostWithAuth(String url, String username, String password) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        //System.out.println(token);
        httpPost.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            resp = client.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }


    public static String writeToText(String xmlContent, String fileName) throws IOException {
        // 生成的文件路径
        String p = PropertyUtil.get("safe.xml.path", "application.properties");
        String path = p + fileName + ".xml";
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        // write 解决中文乱码问题
        // FileWriter fw = new FileWriter(file, true);
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(xmlContent);
        bw.flush();
        bw.close();
        fw.close();
        return path;

    }

    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = "";
            int line = 0;
            while ((s = br.readLine()) != null) {
                if (line == 0) {
                    result.append(s);
                    line++;
                } else {
                    result.append(System.lineSeparator() + s);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * https post
     * ff
     *
     * @param
     * @return
     */

    public static String httpsPostWithAuthWithFormDataWithFile(String url, String username, String password,
                                                               String targets, String taskName) throws Exception {

        File temFile = new File("C:\\fftest\\dem.xml");
//        String temPath = PropertyUtil.get("safe.xml.template","application.properties");
//        File temFile = new File(temPath + "tem.xml");
        String xmlNeirong = String.format(txt2String(temFile), targets, taskName);
        String filePath = writeToText(xmlNeirong, taskName);

        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        CloseableHttpClient httpClient = new SSLClient();
        File file = new File(filePath);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
            //HttpPost httppost = new HttpPost("https://10.21.34.95/api/task/create?username=admin&password=mzRK$tbQ");
            HttpPost httppost = new HttpPost(url);
            httppost.setHeader("Authorization", "Basic " + token);
            //httppost.addHeader("Authorizaiton", authcStringEnc);
            StringBody type = new StringBody("1", ContentType.create("text/plain", Consts.UTF_8));
            FileBody bin = new FileBody(file);
            HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("config_xml", bin)//
                    .addPart("type", type)
                    .build();


            httppost.setEntity(reqEntity);
            httppost.setConfig(config);
            response = httpClient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
            byte[] bytes = new byte[1024];
            if (resEntity != null) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(resEntity.getContent());
                while (bufferedInputStream.read(bytes) != -1) {
                    result += new String(bytes, 0, bytes.length);
                }
            }
            EntityUtils.consume(resEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
            httpClient.close();
        }

        return result;
    }

    /**
     * https post
     * ff
     *
     * @param
     * @return
     */

    public static String httpsPostWithAuthWithFormData(String url, String username, String password,
                                                       Map<String, Object> params) throws UnsupportedEncodingException {
        HttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        httpPost.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            httpClient = new SSLClient();
            resp = httpClient.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respContent;
    }


    /**
     * https get
     * ff
     *
     * @param
     * @return
     */

    public static String httpsGetWithAuth(String url, String username, String password) {
        HttpClient httpClient = null;
        HttpGet httpGet = new HttpGet(url);

        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        httpGet.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            httpClient = new SSLClient();
            resp = httpClient.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static String httpGetWithAuth(String url, String username, String password) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        //System.out.println(token);
        httpGet.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            resp = client.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static String httpDeleteWithAuth(String url, String username, String password) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        //System.out.println(token);
        httpDelete.setHeader("Authorization", "Basic " + token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            resp = client.execute(httpDelete);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static String httpPutWithAuth(String url, String paramStr, String username, String password) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        byte[] tokenByte = Base64.encodeBase64((username + ":" + password).getBytes());
        String token = new String(tokenByte);
        httpPut.setHeader("Authorization", "Basic " + token);
        httpPut.setHeader("Content-type", "application/json");
        String respContent = null;
        HttpResponse resp = null;
        try {
            httpPut.setEntity(new StringEntity(paramStr));
            resp = client.execute(httpPut);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static JSONObject httpPostRequest(String url, JSONObject jsonParam, boolean noNeedResponse) {
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    //jsonResult = JSONObject.parseObject(str);
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
//            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    /**
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResultToUTF8(httpGet);
    }

    /**
     * http get请求 带用户名密码  ob用
     *
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static String HttpGetByAuth(String url, String userName, String password) {
        String auth = userName + ":" + password;
        byte[] result = Base64.encodeBase64(auth.getBytes());

        String result_string = new String(result);

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Basic " + result_string);
        httpGet.setHeader("Content-Type", "application/json");
        return getResult(httpGet);

    }


    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }

//    public static JSONObject httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
//        URIBuilder ub = new URIBuilder();
//        ub.setPath(url);
//
//        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
//        ub.setParameters(pairs);
//
//        HttpGet httpGet = new HttpGet(ub.build());
//        String result = getResult(httpGet);
//        return JSON.parseObject(result);
//    }

    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params) {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(ub.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet);
    }

    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }


    public static String httpsPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        HttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        StringEntity se = new StringEntity(new JSONObject(params).toJSONString());
        se.setContentEncoding("UTF-8");
        se.setContentType("application/json");
        String respContent = null;
        httpPost.setEntity(se);
        HttpResponse resp = null;
        try {
            httpClient = new SSLClient();
            resp = httpClient.execute(httpPost);

            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respContent;
    }

    public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(new JSONObject(params).toJSONString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, String params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        StringEntity entitystring = new StringEntity(params, "utf-8");//解决中文乱码问题

        entitystring.setContentEncoding("UTF-8");
        entitystring.setContentType("application/json");
        httpPost.setEntity(entitystring);

        return getResult(httpPost);
    }

    public static String httpsGetRequest(String url, Map<String, Object> headers, Map<String, Object> params) {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpGet = new HttpGet(ub.build());
            httpGet.addHeader("Content-Type", "application/json");
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }
            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }

        return pairs;
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, String jsonl)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        StringEntity entity = new StringEntity(jsonl, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }

    public static String httpPostWithJSON(String url, String json) {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
////        json??
//        JSONObject jsonParam = ;
        StringEntity entity = new StringEntity(json, "utf-8");//????????
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
//        System.out.println();
//        ????
//        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
//        pairList.add(new BasicNameValuePair("name", "admin"));
//        pairList.add(new BasicNameValuePair("pass", "123456"));
//        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
        HttpResponse resp = null;
        try {
            resp = client.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }


    public static String httpsDeleteWithAJSONRaw(String url, String token) {
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Content-type", "application/json");
        httpDelete.setHeader("X-Auth-Token", token);
        String respContent = null;
        HttpResponse resp = null;
        try {
            HttpClient client = new SSLClient();
            resp = client.execute(httpDelete);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return respContent;
    }


    public static String httpPostWithJSONRaw(String url, String json) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");
        String respContent = null;
        HttpResponse resp = null;
        try {
            resp = client.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respContent;
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            // response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity, "UTF-8");
                response.close();
                // httpClient.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return EMPTY_STR;
    }

    /**
     * 处理Http请求
     *
     * @param request utf8
     * @return
     */
    private static String getResultToUTF8(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            // response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity, "UTF-8");
                response.close();
                // httpClient.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return EMPTY_STR;
    }

    public static String HttpsPostWithHeader(String url, String jsonstr, Map<String, String> header, String charset) {
        HttpPost httpPut = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonstr, "utf-8");
        httpPut.setEntity(entity);
        httpPut.setHeader("Content-type", "application/json");
        for (Map.Entry entry : header.entrySet()) {
            httpPut.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        String respContent = null;
        HttpResponse resp = null;
        try {
            HttpClient client = new SSLClient();
            resp = client.execute(httpPut);
            System.out.println("resp:" + resp);
            if (resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() <= 206) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
                System.out.println("respContent:" + respContent);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return respContent;
    }

}
