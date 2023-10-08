package com.clic.ccdbaas.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AESUtils {

    private static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";
    public static final String CODE_TYPE = "UTF-8";
    public static final String KEY = "0123456789ABCDEF";
    public static final String IV = "0123456789ABCDEF";

    public static String encrypt(String content) {
        if (content == null || "".equals(content)) {
            return content;
        }
        try {
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            IvParameterSpec zeroIv = new IvParameterSpec(IV.getBytes(CODE_TYPE));
            byte[] byteContent = content.getBytes(CODE_TYPE);
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(CODE_TYPE), AES);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, zeroIv);
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            Logger.getLogger(AESUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String decrypt(String content) {
        if (content == null || "".equals(content)) {
            return content;
        }
        try {
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            IvParameterSpec zeroIv = new IvParameterSpec(IV.getBytes(CODE_TYPE));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(CODE_TYPE), AES);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, zeroIv);
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, CODE_TYPE);
        } catch (Exception ex) {
            Logger.getLogger(AESUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}