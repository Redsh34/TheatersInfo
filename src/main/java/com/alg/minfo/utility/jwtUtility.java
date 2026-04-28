package com.alg.minfo.utility;

import com.alg.minfo.entity.user;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class jwtUtility {
    public final String SECRET ="ejwmcsclwcmkwedikwemkwmejje-ejdjwje";
    public final SecretKey SECREY_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECREY_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECREY_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, user user, String username) {
        //check username is same as user details in db
        //check token is not expired
        boolean res= username.equals(user.getUsername()) && !isTokenExpired(token);
        return res;

    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(SECREY_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
