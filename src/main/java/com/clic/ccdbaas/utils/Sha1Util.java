package com.clic.ccdbaas.utils;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Sha1Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogManager.ROOT_LOGGER_NAME);

    /**
     * 计算sha1
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getSha1(String original) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Get SHA1 instance exception");
        }
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuilder hexStr = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexStr.append(0);
            }
            hexStr.append(shaHex);
        }
        return hexStr.toString();
    }

}
