package com.example.thuctap.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    UserDetailsService userDetailsService;
    private long validity = 15; //15 phút

    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();

        Date exprire = new Date(now.getTime() + validity * 60 * 1000);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exprire)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public boolean isValidateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;

        } catch (Exception e) {

        }
        return false;
    }

    public String getUsername(String token){
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Authentication getAuthentication(String username) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }

}
