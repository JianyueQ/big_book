package com.big_event;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.big_event.pojo.Result;
import com.big_event.utils.JwtUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class testGen {
    @Test
    public void token(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        //生成jwt代码
        String token = JWT.create()
                .withClaim("user",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
                .sign(Algorithm.HMAC256("yuxia"));//指定算法，配置密钥
        System.out.println(token);
    }

    @Test
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3NDExODQxMzN9." +
                "bwkYKrbE2tJomVPogto68nCo6M_N1mUJ6erH0bMtzfg";

        Verification require = JWT.require(Algorithm.HMAC256("yuxia"));
        JWTVerifier jwtVerifier = require.build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims);
    }

    @Test
    public void testToken(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username",1);
        String token = JwtUtil.genToken(claims);
        System.out.println(token);
    }
}
