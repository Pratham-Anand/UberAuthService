package com.example.UberAuthService.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {


    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String secret;


    private String createToken(Map<String,Object> payload, String email){

        Date now=new Date();
        Date expiryDate= new Date(now.getTime()+expiry*1000L);

//        SecretKey key= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

//        return Jwts.builder()
//                .claims(payload)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(expiryDate)
//                .subject(username)
//                .signWith(key)
//                .compact();

        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate).subject(email)
                .expiration(expiryDate)
                .subject(email)
                .signWith(getSignKey())
                .compact();

    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    private Boolean validateToken(String token, String email){
        final String userEmailFetchedFromToken=extractEmail(token);
        return (userEmailFetchedFromToken.equals((email)) && !isTokenExpired(token));
    }


    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
    final Claims claims=extractAllPayloads(token);
    return claimsResolver.apply(claims);
    }

    private Claims extractAllPayloads(String token) {
    
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Object extractPayload(String token,String payloadKey){
        Claims claim =extractAllPayloads(token);
        return (Object) claim.get(payloadKey);


    }

    @Override
    public void run(String... args) throws Exception{

        Map<String,Object> mp=new HashMap<>();

        mp.put("email", "a@b.com");
        mp.put("phoneNumber", "9999999999");
        String token = createToken(mp, "sanket");
        System.out.println("Generated token is: " + token);
        System.out.println(extractPayload(token, "email").toString());
    }
}
