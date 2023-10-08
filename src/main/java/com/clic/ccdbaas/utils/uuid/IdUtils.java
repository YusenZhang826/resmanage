package com.clic.ccdbaas.utils.uuid;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;

/**
 * ID生成器工具类
 *
 * @author ruoyi
 */
public class IdUtils {
    private static final DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator();

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 简化的UUID，去掉了横线，小写转大写
     *
     * @return 简化的UUID，去掉了横线，小写转大写
     */
    public static String generatedUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }


    public static Number generatedNextId(Object entity) {
        return defaultIdentifierGenerator.nextId(entity);
    }

}
