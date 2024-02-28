package com.example.coursee;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtCore {

    @Value("${coursee.app.secret}")
    private String secret;

    @Value("${coursee.app.lifetime}")
    private Duration lifeTime;

    // 55 МиНУТА ВИДЕО !!!!
    // https://www.youtube.com/watch?v=NIv9TFTSIlg&ab_channel=%D0%90%D0%BB%D0%B5%D0%BA%D1%81%D0%B0%D0%BD%D0%B4%D1%80%D0%A4%D0%B8%D1%81%D1%83%D0%BD%D0%BE%D0%B2

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);
        Date date = new Date();
        Date expiredDate = new Date(date.getTime() + lifeTime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(date)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret).compact();

    }

    public String getUserNameFromToken(String token){
        return getAllClaims(token).getSubject();

    }

    public List<String> getRoles(String token){
        return getAllClaims(token).get("roles", List.class);
    }

    private Claims getAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

   /*  Cтарый метод-------------------
   public String generateToken(Authentication authentication){

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject((userDetails.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + lifeTime))
                .signWith(SignatureAlgorithm.HS256, secret).compact();


    }

    public String getNameFromJWT(String token) {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();


    }*/
}
