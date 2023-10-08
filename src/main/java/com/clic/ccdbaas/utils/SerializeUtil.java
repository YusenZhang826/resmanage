package com.clic.ccdbaas.utils;

import java.io.*;

public class SerializeUtil {

    /**
     * 序列化
     *
     * @param obj 对象
     * @return 序列化后的字节码数组
     */
    public static byte[] serialize(Object obj) {
        ObjectOutputStream obi = null;
        ByteArrayOutputStream bai = null;

        try {
            bai = new ByteArrayOutputStream();
            obi = new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt = bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param byt 字节码数组
     * @return 反序列化后的对象
     */
    public static Object unserizlize(byte[] byt) {
        ObjectInputStream oii = null;
        ByteArrayInputStream bis = null;
        bis = new ByteArrayInputStream(byt);

        try {
            oii = new ObjectInputStream(bis);
            Object obj = oii.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
