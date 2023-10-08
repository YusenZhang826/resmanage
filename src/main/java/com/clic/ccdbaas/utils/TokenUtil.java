package com.clic.ccdbaas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TokenUtil {
    private static final long EXPIRE_TIME=30*60*1000;
    private static final String TOKEN_SECRET = "token_123";

    //生成签名
    public static String sign(String name, String userId){
        String token = null;
        try{
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0").withClaim("id","id")
                    .withClaim("username",name)
                    .withClaim("userId",userId)
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    //签名验证
    public static  boolean verify(String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public static String getId(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(token);
        String id = jwt.getClaim("userId").asString();
        return id;
    }

    public static long getExpireTime() {
        return EXPIRE_TIME;
    }
}

