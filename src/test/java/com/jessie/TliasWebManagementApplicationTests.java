package com.jessie;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasWebManagementApplicationTests {

    /**
     * 生成jwt
     */
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name", "Tom");

        String jwt = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, "jessie") // 签名算法
                    .setClaims(claims) //自定义内容
                    .setExpiration(new Date(System.currentTimeMillis() + 3600*1000)) // 设置有效期为1h
                    .compact();

        System.out.println(jwt);
    }


    /**
     * 校验令牌
     */
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                            .setSigningKey("jessie") // 指定签名密钥（必须和生成令牌时的密钥时一样的）
                            .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTcwMjAxODU1OX0.dSZBGQCJ9YqjhducQkMNZ26Rzjys58RallTXikACa9c") // 解析令牌
                            .getBody();
        // 令牌解析时报错，说明令牌被篡改或者已经过期
        System.out.println(claims);
    }

}
