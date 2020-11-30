package cn.alan.digest;

public class DigestTest {
    public static void main(String[] args) {
        // 原文
        String input = "aa";
        System.out.println("MD5  === " + DigestUtils.digestMD5(input));
        System.out.println("Sha1  === " + DigestUtils.digestSha1(input));
        System.out.println("Sha256  === " + DigestUtils.digestSha256(input));
        System.out.println("Sha512  === " + DigestUtils.digestSha512(input));
    }

}
