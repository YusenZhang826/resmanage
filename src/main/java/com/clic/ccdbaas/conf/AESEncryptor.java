package com.clic.ccdbaas.conf;

import com.clic.ccdbaas.utils.AESUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("desencrypt")
public class AESEncryptor implements StringEncryptor {
    private static final Logger logger = LoggerFactory.getLogger(AESEncryptor.class);

    @Override
    public String encrypt(String message) {
        if (StringUtils.isNotBlank(message)) {
            try {
                logger.info("加密开始");
                message = AESUtils.encrypt(message);
                logger.info("加密后密码：" + message);
                logger.info("配置信息加密成功!");
            } catch (Exception e) {
                logger.error("配置信息加密失败!");
            }
        }
        return message;
    }

    @Override
    public String decrypt(String encryptedMessage) {
        if (StringUtils.isNotBlank(encryptedMessage)) {
            try {
                logger.info("解密前密码：" + encryptedMessage);
                encryptedMessage = AESUtils.decrypt(encryptedMessage);
                logger.info("配置信息解密成功!");
            } catch (Exception e) {
                logger.error("配置信息解密失败!");
            }
        }
        return encryptedMessage;
    }
}

