package cn.alan.base62;

import java.io.ByteArrayOutputStream;

public class Base62Utils {
    /**
     * 特别注意： Base62编码与解码的key中，必须包含有“+/”这两个字符，<br/>
     * 否则，会有出现一个数字不能正常解码
     */
    private static char[] encodes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static byte[] decodes = new byte[256];
    static {
        for (int i = 0; i < encodes.length; i++) {
            decodes[encodes[i]] = (byte)i;
        }
    }

    /**
     * 字符串进行base62编码
     * 
     * @param input
     *            原串
     * @return base62编码后的串
     */
    public static String encode(String input) {
        return encode(input.getBytes());

    }

    /**
     * 字节数组进行base62编码
     * 
     * @param data
     *            字节数组
     * @return base62编码后的串
     */
    public static String encode(byte[] data) {
        StringBuffer sb = new StringBuffer(data.length * 2);
        int pos = 0, val = 0;
        for (int i = 0; i < data.length; i++) {
            val = (val << 8) | (data[i] & 0xFF);
            pos += 8;
            while (pos > 5) {
                char c = encodes[val >> (pos -= 6)];
                sb.append(c == 'i' ? "ia" : c == '+' ? "ib" : c == '/' ? "ic" : String.valueOf(c));
                val &= ((1 << pos) - 1);
            }
        }
        if (pos > 0) {
            char c = encodes[val << (6 - pos)];
            sb.append(c == 'i' ? "ia" : c == '+' ? "ib" : c == '/' ? "ic" : String.valueOf(c));
        }
        return sb.toString();
    }

    /**
     * base62 解码
     * 
     * @param encodeData
     *            字符串
     * @return 字节数组
     */
    public static byte[] decode(String encodeData) {
        if (encodeData == null) {
            return null;
        }
        char[] data = encodeData.toCharArray();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(encodeData.toCharArray().length);
        int pos = 0, val = 0;
        for (int i = 0; i < data.length; i++) {
            char c = data[i];
            if (c == 'i') {
                c = data[++i];
                c = c == 'a' ? 'i' : c == 'b' ? '+' : c == 'c' ? '/' : data[--i];
            }
            val = (val << 6) | decodes[c];
            pos += 6;
            while (pos > 7) {
                bos.write(val >> (pos -= 8));
                val &= ((1 << pos) - 1);
            }
        }
        return bos.toByteArray();
    }

}
