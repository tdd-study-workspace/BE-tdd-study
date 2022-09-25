package com.tdd.study.betddstudy.global.security;

import com.tdd.study.betddstudy.api.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret-key}")
    private String secret_key;

    @Value("${jwt.token.expire-length}")
    private long expire_time;

    private UserService userService;

    public String generateToken(Authentication authentication) {

        Claims claims = Jwts.claims().setSubject(authentication.getName());

        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + expire_time);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresIn)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = userService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // MalformedJwtException | ExpiredJwtException | IllegalArgumentException
            throw new RuntimeException("Error on Token");
        }
    }
}
