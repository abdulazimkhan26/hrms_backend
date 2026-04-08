package com.hrms.admin.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.List;

@Component
public class jwtFilter extends OncePerRequestFilter {

    private String secretKey = "bWlncmF0aW9uU2VjcmV0S2V5Rm9ySE1BQ1NIQTI1NkFsZ29yaXRobQ==";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                byte[] keyBytes = Decoders.BASE64.decode(secretKey);  // ✅ matches auth service
                Key key = Keys.hmacShaKeyFor(keyBytes);

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String role = (String) claims.get("role"); // extract role from token
                System.out.println(role);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                claims.getSubject(),
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("Auth set: " + auth.getAuthorities()); // ← add this
            }
             catch (Exception e) {
                System.out.println("JWT Error: " + e.getClass().getName() + " - " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}