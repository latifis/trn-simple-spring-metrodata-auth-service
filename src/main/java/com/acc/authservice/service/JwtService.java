package com.acc.authservice.service;

import com.acc.authservice.entity.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final String SECRET_KEY="A5547CFEA07EF4B8A824C307425C447EA3077DDC446642882F35BFF0A4F583D8F3A82B5C6B7A19210453D120C6E8775EBF48FAEEDA1B7FB9ED0A5630BE0A508347E56CDE472FC4FEC7BD6CBB6D9B1236601891415482377DBB8771CB2F2A38E4AD72B4AC9ACA460A76A484238E7BAD50AC038959D442937F735072AB8018C9A6";

    public String generateToken(UserDetail userDetail) {
        return generateToken(userDetail, new HashMap<>());
    }

    public String generateToken(UserDetail userDetail,
                                Map<String, Object> claims) {
        List<String> roles = userDetail.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetail.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSignKey() {
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

    public Claims validate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
