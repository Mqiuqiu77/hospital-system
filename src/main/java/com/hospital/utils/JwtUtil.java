package com.hospital.utils;

import com.hospital.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;

import io.jsonwebtoken.Jwts;
import java.util.Date;

@Component
public class JwtUtil {
    private final JwtProperties jwtProperties;
    private final SecretKey signingKey;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        byte[] keyBytes =
                Decoders.BASE64.decode(jwtProperties.getSecret());

        this.signingKey = Keys.hmacShaKeyFor(keyBytes);

    }

    public String generateToken(Long userId, String username,String role,Integer tokenVersion){
        Instant now = Instant.now();
        Instant expiration = now.plus(jwtProperties.getExpiration());
        return Jwts.builder().subject(userId.toString())
                .claim("username",username).claim("role",role).claim("tokenVersion",tokenVersion)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(signingKey)
                .compact();
    }


    public Claims parseToken(String token) {
        return Jwts.parser()
//                 指定用于验签的密钥
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getUserId(Claims claims) {
        return Long.valueOf(claims.getSubject());
    }

    public String getUsername(Claims claims) {
        return claims.get("username", String.class);
    }

    public String getRole(Claims claims){

        return claims.get("role",String.class);
    }

    public Integer getTokenVersion(Claims claims){
        return claims.get("tokenVersion",Integer.class);
    }
}
