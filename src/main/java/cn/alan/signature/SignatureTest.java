package cn.alan.signature;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import rsa.RsaUtils;

public class SignatureTest {
    public static void main(String[] args) throws Exception {
        String input = "123";
        Key[] keys = RsaUtils.genKey();
        PrivateKey privateKey = (PrivateKey)keys[0];
        PublicKey publicKey = (PublicKey)keys[1];

        String signaturedData = SignatureUtils.signature(input, privateKey);

        boolean b = SignatureUtils.verifySignature(input, publicKey, signaturedData);
        System.out.println("校验结果：" + b);
    }
}
