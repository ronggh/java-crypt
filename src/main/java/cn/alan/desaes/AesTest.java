package cn.alan.desaes;

public class AesTest {
    private final static String transformation = "AES/CBC/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        String input = "中国";
        // AES加密算法，比较高级，所以key的大小必须是16个字节
        String key = "1234567812345678";

        // 先测试加密，然后在测试解密
        String encryptAes = encryptAes(input, key);
        System.out.println("加密:" + encryptAes);
        String s = decryptAes(encryptAes, key);
        System.out.println("解密:" + s);

    }

    /**
     * 使用AES加密数据
     *
     * @param input
     *            : 原文
     * @param key
     *            : 密钥(AES,密钥的长度必须是16个字节)
     * @return : 密文
     * @throws Exception
     */
    private static String encryptAes(String input, String key) throws Exception {
        return DesAesUtils.encrypt(input, key, transformation, "AES");
    }

    /**
     * 使用AES解密
     *
     * @param input
     *            : 密文
     * @param key
     *            : 密钥
     * 
     * @throws Exception
     * @return: 原文
     */
    private static String decryptAes(String input, String key) throws Exception {
        return DesAesUtils.decrypt(input, key, transformation, "AES");
    }

}
