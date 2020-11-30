package cn.alan.signature;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class SignatureUtils {
    private static final String algorithm = "sha256withrsa";

    /**
     * 
     * @param input
     *            原文
     * @param privateKey
     *            私钥
     * @return 签名
     * @throws Exception
     */
    public static String signature(String input, PrivateKey privateKey) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 开始签名
        byte[] sign = signature.sign();
        // 对签名数据进行Base64编码
        return Base64.getEncoder().encodeToString(sign);
    }

    /**
     * 校验签名
     *
     * @param input
     *            : 原文
     * @param publicKey
     *            : 公钥
     * @param signaturedData
     *            : 签名
     * @return : 数据是否被篡改
     * @throws Exception
     */
    public static boolean verifySignature(String input, PublicKey publicKey, String signaturedData) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验数据
        return signature.verify(Base64.getDecoder().decode(signaturedData));

    }
}
