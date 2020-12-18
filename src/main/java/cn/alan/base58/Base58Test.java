package cn.alan.base58;

/**
 * @author alan
 * @date 2020/12/3
 */
public class Base58Test {
    public static void main(String[] args) {
        String input = "中国China";
        String encode = Base58Utils.encode(input);
        System.out.println("encode ==> " + encode);
        byte[] bytes = Base58Utils.decode(encode);
        String decode = new String(bytes);
        System.out.println("decode ==> " + decode);

    }
}
