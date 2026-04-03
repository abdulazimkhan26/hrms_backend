package com.hrms.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private final String secret_key = "bWlncmF0aW9uU2VjcmV0S2V5Rm9ySE1BQ1NIQTI1NkFsZ29yaXRobQ==";
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);  // properly decoded
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, Map<String,Object> extraClaims) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaims(String Token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(Token)
                .getPayload();
    }

    public String returnClaims(String Token){
        Claims claims = extractClaims(Token);
        String role = claims.get("role", String.class);

        return role;
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

}
