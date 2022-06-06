package com.viktor.task.shoes.manager.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

// @Service
public class JWTUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.sessionTime}")
    private long sessionTime;
    // генерация токена (кладем в него имя пользователя и authorities)
//     public String generateToken(UserDetails userDetails) {
//         Map<String, Object> claims = new HashMap<>();
//         String commaSeparatedListOfAuthorities=  userDetails.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(","));
//         claims.put("authorities", commaSeparatedListOfAuthorities);
//         return createToken(claims, userDetails.getUsername());
//     }
    
//     private String createToken(Map<String, Object> claims, String username) {
//         return createToken(claims,username);
//     }

//     //извлечение имени пользователя из токена (внутри валидация токена)
//     public String extractUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }
  

//     private String extractClaim(String token, Object object) {
//         return extractClaim( token, object);
//     }

//     //извлечение authorities (внутри валидация токена)
//     public String extractAuthorities(String token) {
//        return extractClaim(token, claims -> (String)claims.get("authorities"));
//     }
//    // другие методы

   
}