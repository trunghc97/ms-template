package com.example.trunghc.config;

import com.example.trunghc.dto.sercurity.UserPrincipal;
import com.example.trunghc.model.Token;
import com.example.trunghc.repositories.TokenRepository;
import com.example.trunghc.utility.JwtUtility;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;

    private final TokenRepository tokenRepository;

    public JwtRequestFilter(JwtUtility jwtUtility, TokenRepository tokenRepository) {
        this.jwtUtility = jwtUtility;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        UserPrincipal user = null;
        Token token = null;
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(6);
            if (!jwt.isBlank()) {
                user = jwtUtility.getUserFromToken(jwt);
                token = tokenRepository.findByToken(jwt.trim());
            }
        }

        if (null != user && null != token && token.getTokenExpDate().after(new Date())) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            user.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority((String) p)));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

}
