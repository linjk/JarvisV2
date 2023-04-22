package cn.linjk.jarvis.authmanagement.util;

import cn.linjk.jarvis.common.bean.RedisKeyConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author linjk
 */
@Component
public class RSAUtil implements InitializingBean {
    @Resource private StringRedisTemplate stringRedisTemplate;

    private static final String ALGORITHM = "RSA";
    private static final Integer KEY_SIZE = 1024;

    private KeyPair keyPair;

    private static final String redisDefaultPrivateKey = RedisKeyConstant.KEY_PREFIX.getKey().concat("RSA_DEFAULT_PRIVATEKEY");
    private static final String redisDefaultPublicKey = RedisKeyConstant.KEY_PREFIX.getKey().concat("RSA_DEFAULT_PUBLICKEY");

    public String encryptWithDefaultPublicKey(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] bytes = plainText.getBytes();
        bytes = cipher.doFinal(bytes);
        return new String(Base64.encodeBase64(bytes));
    }

    public String decryptWithDefaultPrivateKey(String encText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        return new String(cipher.doFinal(Base64.decodeBase64(encText.getBytes())));
    }

    public String getDefaultPublicKey() {
        return stringRedisTemplate.opsForValue().get(redisDefaultPublicKey);
    }

    public String getDefaultPrivateKey() {
        return stringRedisTemplate.opsForValue().get(redisDefaultPrivateKey);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);

        keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        if (StringUtils.isNoneBlank(stringRedisTemplate.opsForValue().get(redisDefaultPrivateKey))
                && StringUtils.isNoneBlank(stringRedisTemplate.opsForValue().get(redisDefaultPublicKey))) {
            // 已经存在上次生成的公私钥信息，删除
            stringRedisTemplate.delete(redisDefaultPrivateKey);
            stringRedisTemplate.delete(redisDefaultPublicKey);
        }
        stringRedisTemplate.opsForValue().set(redisDefaultPrivateKey, new String(Base64.encodeBase64(privateKey.getEncoded())));
        stringRedisTemplate.opsForValue().set(redisDefaultPrivateKey, new String(Base64.encodeBase64(publicKey.getEncoded())));
    }
}
