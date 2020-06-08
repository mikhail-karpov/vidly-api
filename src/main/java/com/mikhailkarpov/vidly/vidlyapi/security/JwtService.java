package com.mikhailkarpov.vidly.vidlyapi.security;

import com.mikhailkarpov.vidly.vidlyapi.exception.JwtCannotBeTrustedException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private String secret = "BeLV2mmHnHCRSE3hzp4DgBxt8THmGzRrMw8WMItizxqhDi1bzZ2lnIIV9gVvTQf5";
    private int tokenExpirationInMinutes = 60 * 24;

    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiresAt = new Date(now + 1000 * 60 * tokenExpirationInMinutes);

        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .setSubject(username)
                .signWith(getKey())
                .compact();
    }

    public String getUsername(String jwt) throws JwtCannotBeTrustedException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

        } catch (JwtException e) {
            throw new JwtCannotBeTrustedException(e);
        }
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
