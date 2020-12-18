package cn.alan.jwt;

import java.util.Map;

import io.jsonwebtoken.*;

/**
 * JWT生成token，可用于用户身份认证
 */
public class JwtUtils {
    /**
     * 加密算法
     * 
     * @param key
     *            一般用于标识不同服务器
     * @param param
     *            一般是用户信息
     * @param salt，一般用IP，时间戳等
     * @return
     */
    public static String encode(String key, Map<String, Object> param, String salt) {
        if (null != salt) {
            key += salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, key);
        jwtBuilder = jwtBuilder.setClaims(param);
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 
     * @param token
     * @param key
     * @param salt
     * @return
     */
    public static Map<String, Object> decode(String token, String key, String salt) {
        Claims claims = null;
        if (salt != null) {
            key += salt;
        }
        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            return null;
        }
        return claims;
    }
}
