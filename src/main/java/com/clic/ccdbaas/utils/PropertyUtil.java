package com.clic.ccdbaas.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;


public class PropertyUtil {

    private static final HashMap<String, Properties> propertiesMap = new HashMap<String, Properties>();

    public static String get(String key, String filename) throws IOException {
        Properties properties = PropertyUtil.propertiesMap.get(filename);
        if (properties == null) {
            InputStream inStream = PropertyUtil.class.getClassLoader().getResourceAsStream(filename);
            properties = new Properties();
            properties.load(inStream);
            propertiesMap.put(filename, properties);
        }
        String val = properties.getProperty(key);
        return val;
    }

    public static int getInt(String key, String filename) throws IOException {
        Properties properties = PropertyUtil.propertiesMap.get(filename);
        if (properties == null) {
            InputStream inStream = PropertyUtil.class.getClassLoader().getResourceAsStream(filename);
            properties = new Properties();
            properties.load(inStream);
            propertiesMap.put(filename, properties);
        }
        int val = Integer.parseInt((String) properties.get(key));
        return val;
    }


    public static void main(String[] args) throws Exception {
        String tmp = PropertyUtil.get("connection.url", "jdbc.properties");
        System.out.println(tmp);
        String host = PropertyUtil.get("host", "cloud.properties");
        System.out.println(host);
    }
}
