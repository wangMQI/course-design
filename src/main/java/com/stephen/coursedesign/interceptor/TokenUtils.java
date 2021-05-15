package com.stephen.coursedesign.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/19 10:48
 * @Version:
 * @Description:
 */
public class TokenUtils {
    //设置过期时间
    private static final long EXPIRE_DATE=30*60*1000;
    //token秘钥
    private static final String TOKEN_SECRET = "ncdslikfujhhNL;CIUDSJK4ASXAS35DAS";


    //生成token
    public static String getToken (String userPhone,String password){

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(password);  //此处可换为自定义密钥
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带userPhone，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withAudience(userPhone)
                    //.withClaim("userPhone",userPhone) //实际使用中，此处计划改为用户id
                    .withClaim("password",password).withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }


    public static boolean verifyToken(String token,String password){
        /**
         * @desc   验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public static void main(String[] args) {
        String username ="zhangsan";
        String password = "123";
        String token = getToken(username,password);
        System.out.println(token);
        boolean b = verifyToken(token,password);
        System.out.println(b);
    }
}





















/*

public class JwtTokenUtil {

    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks"); // 寻找证书文件
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, "123456".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray()); // jwt 为 命令生成整数文件时的别名
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateToken(String subject, int expirationSeconds, String salt) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥私钥
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public static String parseToken(String token, String salt) {
        String subject = null;
        try {
            Claims claims = Jwts.parser()
//                    .setSigningKey(salt) // 不使用公钥私钥
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

}
*/
