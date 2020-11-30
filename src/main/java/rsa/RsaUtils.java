package rsa;

import java.io.File;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.apache.commons.io.FileUtils;

public class RsaUtils {
    private static final String algorithm = "RSA";

    /**
     * 生成一对秘钥
     * 
     * @return
     * @throws Exception
     */
    public static KeyPair genKeyPair() throws Exception {
        // 创建密钥对生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 生成一对秘钥,[0]为私钥，[1]为公钥
     * 
     * @return
     * @throws Exception
     */
    public static Key[] genKey() throws Exception {
        // 生成密钥对
        KeyPair keyPair = genKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();

        return new Key[] {privateKey, publicKey};
    }

    /**
     * 秘钥转换成字符串，Base64编码格式
     * 
     * @param key
     * @return
     */
    public static String key2String(Key key) {
        // 获取私钥字节数组
        byte[] keyEncoded = key.getEncoded();

        // 对钥进行base64编码
        String keyString = Base64.getEncoder().encodeToString(keyEncoded);
        return keyString;
    }

    /**
     * 保存秘钥到文件
     * 
     * @param key
     * @param fileName
     * @throws Exception
     */
    public static void saveKey2File(String key, String fileName) throws Exception {
        FileUtils.writeStringToFile(new File(fileName), key);
    }

    /**
     * 从文件中读取公钥
     * 
     * @param fileName
     * @return
     * @throws Exception
     */
    public static PublicKey readPublicKeyFromFile(String fileName) throws Exception {
        //
        String keyString = FileUtils.readFileToString(new File(fileName));
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(keyString));
        // 生成公钥
        return keyFactory.generatePublic(spec);
    }

    /**
     * 从文件中读取私钥
     * 
     * @param fileName
     * @return
     * @throws Exception
     */
    public static PrivateKey readPrivateKeyFromFile(String fileName) throws Exception {
        //
        String keyString = FileUtils.readFileToString(new File(fileName));
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyString));
        // 生成私钥
        return keyFactory.generatePrivate(spec);
    }

    /**
     * 使进行解密
     * 
     * @param input
     *            原串
     * @param key
     *            使用密钥进行加密，可以是公钥或私钥
     * @return
     * @throws Exception
     */
    public static String decode(String input, Key key) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化加密
        // 第一个参数:加密的模式
        // 第二个参数：使用密钥进行解密，可以是公钥或私钥
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 由于密文进行了Base64编码, 需要先进行解码
        byte[] decode = Base64.getDecoder().decode(input);
        byte[] bytes = cipher.doFinal(decode);
        // 原文不会乱码，不需要进行Base64
        return new String(bytes);
    }

    /**
     * 进行加密
     * 
     * @param input
     * @param key
     *            使用密钥进行加密，可以是公钥或私钥
     * @return
     * @throws Exception
     */
    public static String encode(String input, Key key) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化加密
        // 第一个参数:加密的模式
        // 第二个参数：使进行加密，可以是公钥或私钥
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 私钥加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }
}
