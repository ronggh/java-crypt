package cn.alan.digest;

public class DigestFileTest {
    public static void main(String[] args) {
        // 原文
        String filePath =
            "F:\\MyProject\\JavaProject\\java-crypt\\src\\main\\java\\cn\\alan\\digest\\DigestFileUtils.java";
        System.out.println("MD5  === " + DigestFileUtils.digestFileMD5(filePath));
        System.out.println("Sha1  === " + DigestFileUtils.digestFileSha1(filePath));
        System.out.println("Sha256  === " + DigestFileUtils.digestFileSha256(filePath));
        System.out.println("Sha512  === " + DigestFileUtils.digestFileSha512(filePath));
    }

}
