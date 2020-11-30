package cn.alan.digest;

import java.security.MessageDigest;

public class DigestUtils {
    public static String digestMD5(String input) {
        return digest(input, "MD5");
    }

    public static String digestSha1(String input) {
        return digest(input, "SHA-1");
    }

    public static String digestSha256(String input) {
        return digest(input, "SHA-256");
    }

    public static String digestSha512(String input) {
        return digest(input, "SHA-512");
    }

    private static String digest(String input, String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 获取消息数字摘要的字节数组
            byte[] digestBytes = messageDigest.digest(input.getBytes());
            // 转成16进制
            return HexUtils.toHex(digestBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
