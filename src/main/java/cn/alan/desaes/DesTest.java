package cn.alan.desaes;

public class DesTest {
    // private final static String transformation = "DES/CBC/NoPadding";
    private final static String transformation = "DES/CBC/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        // 原文
        String input = "中国";
        // des加密必须是8位
        String key = "12345678";

        String encryptDES = encryptDES(input, key);
        System.out.println("加密:" + encryptDES);
        String s = decryptDES(encryptDES, key);
        System.out.println("解密:" + s);
    }

    /**
     * 使用DES加密数据
     *
     * @param input
     *            : 原文
     * @param key
     *            : 密钥(DES,密钥的长度必须是8个字节)
     * @return : 密文
     * @throws Exception
     */
    private static String encryptDES(String input, String key) throws Exception {
        return DesAesUtils.encrypt(input, key, transformation, "DES");
    }

    /**
     * 使用DES解密
     *
     * @param input
     *            : 密文
     * @param key
     *            : 密钥
     * @throws Exception
     * @return: 原文
     */
    private static String decryptDES(String input, String key) throws Exception {
        return DesAesUtils.decrypt(input, key, transformation, "DES");
    }
}
