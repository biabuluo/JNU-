package cn.edu.jnu.devhub.util;

import io.jsonwebtoken.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 小宇
 * @date {2023}-{07}-{15}:{0:00}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class JwtUtils {
    private static long time = 1000*60*60*1;
    private static String sign = "devhub";

    public static String createJwt(String username, String role){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("username", username)
                .claim("role", role)
                .setSubject("identity")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, sign)
                .compact();
        return jwtToken;
    }

    public static boolean checkJwt(String token){
        return Jwts.parser().isSigned(token);
    }

    public static List<String> parseJwt(String token){
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJwts = jwtParser.setSigningKey(sign)
                .parseClaimsJws(token);
        Claims claims = claimsJwts.getBody();
        String username = claims.get("username").toString();
        String role = claims.get("role").toString();
        List<String> strings = new ArrayList<>();
        strings.add(username);
        strings.add(role);
        return strings;
    }

    //验证是否过期
    public static boolean isExpire(String token){
        if(token==null||token=="") return true;
        try{
            Jws<Claims> claimsJws= Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
