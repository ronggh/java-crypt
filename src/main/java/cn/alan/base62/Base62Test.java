package cn.alan.base62;

/**
 * @author alan
 */
public class Base62Test {
    public static void main(String[] args) {
        String input = "中国China";
        String encode = Base62Utils.encode(input);
        System.out.println("encode ==> " + encode);
        byte[] bytes = Base62Utils.decode(encode);
        String decode = new String(bytes);
        System.out.println("decode ==> " + decode);
    }
}
