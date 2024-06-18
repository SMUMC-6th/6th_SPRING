package com.example.umc.study.auth.filter;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.AuthHandler;
import com.example.umc.study.auth.userdetails.PrincipalDetailsService;
import com.example.umc.study.auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final PrincipalDetailsService principalDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = jwtUtil.resolveAccessToken(request);

        if(jwtUtil.isTokenValid(accessToken)) {
            String email = jwtUtil.getEmail(accessToken);
            UserDetails userDetails = principalDetailsService.loadUserByUsername(email);

            if (userDetails != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, "", userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                throw new AuthHandler(ErrorStatus._NOT_FOUND_USER);
            }
        }

        filterChain.doFilter(request, response);
    }
}
