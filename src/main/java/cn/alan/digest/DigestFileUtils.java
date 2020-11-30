package cn.alan.digest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class DigestFileUtils {
    public static String digestFileMD5(String filePath) {
        return digestFile(filePath, "MD5");
    }

    public static String digestFileSha1(String filePath) {
        return digestFile(filePath, "SHA-1");
    }

    public static String digestFileSha256(String filePath) {
        return digestFile(filePath, "SHA-256");
    }

    public static String digestFileSha512(String filePath) {
        return digestFile(filePath, "SHA-512");
    }

    private static String digestFile(String filePath, String algorithm) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            int len;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            // 获取消息摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 获取消息摘要
            byte[] digest = messageDigest.digest(baos.toByteArray());
            return HexUtils.toHex(digest);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
