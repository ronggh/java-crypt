package rsa;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaTest {
    public static void main(String[] args) throws Exception {
        Key[] keys = RsaUtils.genKey();
        PrivateKey privateKey = (PrivateKey)keys[0];
        PublicKey publicKey = (PublicKey)keys[1];
        System.out.println("privateKey Key ==> " + RsaUtils.key2String(privateKey));
        System.out.println("publicKey Key ==> " + RsaUtils.key2String(publicKey));

        String privateKeyString = RsaUtils.key2String(privateKey);
        RsaUtils.saveKey2File(privateKeyString, "pri.rsa");

        String publicKeyString = RsaUtils.key2String(publicKey);
        RsaUtils.saveKey2File(publicKeyString, "pub.rsa");

        System.out.println("================= 从保存的文件中读取 Key ===========");
        PrivateKey priKey = RsaUtils.readPrivateKeyFromFile("pri.rsa");
        PublicKey pubKey = RsaUtils.readPublicKeyFromFile("pub.rsa");
        System.out.println("从文件中读取的 private Key ==> " + RsaUtils.key2String(priKey));
        System.out.println("从文件中读取的 public Key ==> " + RsaUtils.key2String(pubKey));

        String input = "中国";
        System.out.println("原文 ==> " + input);
        String encode = RsaUtils.encode(input, privateKey);
        System.out.println("私钥加密，密文 ==> " + encode);

        String decode = RsaUtils.decode(encode, publicKey);
        System.out.println("公钥解密，原文 ==> " + decode);

        System.out.println("======================");

        encode = RsaUtils.encode(input, publicKey);
        System.out.println("公钥加密，密文 ==> " + encode);

        decode = RsaUtils.decode(encode, privateKey);
        System.out.println("私钥解密，原文 ==> " + decode);
    }
}
