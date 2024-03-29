package com.dosi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
/*
@Service
public class JwtService {

    private static final String SECRET_KEY = "7638782F413F4428472B4B6250655368566D597133743677397A244226452948";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token , Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken( UserDetails userDetails)
    {
        return generateToken( new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails ){
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        Map<String, Object> claims = new HashMap<>();
        claims.putAll(extraClaims);
        claims.put("role", role);
       return Jwts
               .builder()
               .setClaims(claims)
               .setSubject(userDetails.getUsername())
               .setIssuedAt( new Date(System.currentTimeMillis()))
               .setExpiration( new Date(System.currentTimeMillis() + 1000 * 60 * 2400 ))
               .signWith( getSigningKey(), SignatureAlgorithm.HS256)
               .compact();
    }

    public boolean isTokenValid( String token, UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before( new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim( token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
       return Jwts
               .parserBuilder()
               .setSigningKey(getSigningKey())
               .build()
               .parseClaimsJws(token)
               .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
*/