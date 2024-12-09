package com.example.recetas;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.recetas.Constants.*;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private SecretKey getSigningKey() {
        // Convierte la clave secreta en una instancia de SecretKey
        return new SecretKeySpec(SUPER_SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
    }

    private Claims getClaimsFromToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER_AUTHORIZACION_KEY).replace(TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private void setAuthentication(Claims claims) {
        Object authoritiesClaim = claims.get("authorities");

        // Verificar que el claim "authorities" es una lista y convertirla de forma segura
        List<String> authorities = authoritiesClaim instanceof List<?> ?
                ((List<?>) authoritiesClaim).stream()
                        .map(Object::toString) // Convertir cada elemento a String
                        .collect(Collectors.toList()) : List.of();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                authorities.stream()
                           .map(SimpleGrantedAuthority::new)
                           .collect(Collectors.toList())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean isJWTValid(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
        return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_BEARER_PREFIX);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (isJWTValid(request)) {
                Claims claims = getClaimsFromToken(request);
                if (claims.get("authorities") != null) {
                    setAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or expired token.");
        }
    }
}
