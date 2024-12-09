package com.example.recetas;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.recetas.Constants.SUPER_SECRET_KEY;

@Configuration
public class JWTAuthenticationConfig {

    private SecretKey getSigningKey() {
        // Convierte la clave secreta en una instancia de SecretKey
        return new SecretKeySpec(SUPER_SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
    }

    public String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1440))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Usa SecretKey aquí
                .compact();

        return "Bearer " + token;
    }
}
