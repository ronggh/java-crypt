package cn.alan.digest;

public class HexUtils {
    public static String toHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            // 转成16进制

            String s = Integer.toHexString(b & 0xff);
            // 保持数据的完整性，前面不够的用0补齐
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
