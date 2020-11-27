package cn.alan.desaes;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DesAesUtils {
    /**
     * 加密方法
     * 
     * @param input
     * @param key
     * @param transformation
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static String encrypt(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数key的字节
        // 第二个参数表示加密算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // ENCRYPT_MODE：加密模式
        // DECRYPT_MODE: 解密模式
        // 初始化加密模式和算法
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());

        // 输出加密后的数据
        String encode = Base64.getEncoder().encodeToString(bytes);

        return encode;
    }

    /**
     * 解密方法
     * 
     * @param input
     * @param key
     * @param transformation
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static String decrypt(String input, String key, String transformation, String algorithm) throws Exception {
        // 1,获取Cipher对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定密钥规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE, sks);
        // 3. 解密，上面使用的base64编码，下面直接用密文
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(input));
        // 因为是明文，所以直接返回
        return new String(bytes);
    }
}
