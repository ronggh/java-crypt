package cn.alan.jwt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", "1");
        map.put("nickname", "zhangsan");
        String ip = "127.0.0.1";
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String key = "2019gmall0105";
        String salt = ip + time;
        String encode = JwtUtils.encode(key, map, salt);
        System.err.println(encode);

        Map<String, Object> decodeMap = JwtUtils.decode(encode, key, salt);
        System.out.println(decodeMap.toString());
    }
}
