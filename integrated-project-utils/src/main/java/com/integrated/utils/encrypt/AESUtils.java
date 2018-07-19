package com.integrated.utils.encrypt;

import javax.crypto.KeyGenerator;
import java.security.SecureRandom;

/**
 * ClassName: AESUtils
 * Description: AES加密解密
 * Author: liangchao
 * Date: 2018/7/12 16:40
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class AESUtils {

    private int bufferSize = 2048;

    /**
     * @Description 默认的加密长度
     * @author liangchao
     * @date 2018/7/12 16:42
     */
    private static final Integer DECRYPT_BLOCK = 128;

    /**
     * @Description 默认的加密算法
     * @author liangchao
     * @date 2018/7/12 16:41
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static byte[] encrypt(byte[] byteContent, String password) throws Exception {

        // 获取AES生产者
        KeyGenerator aes = KeyGenerator.getInstance("AES");

        //你用用户密码作为随机数初始化出
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());

        //初始化aes
        aes.init(DECRYPT_BLOCK, secureRandom);

        return new byte[5];
    }
}
