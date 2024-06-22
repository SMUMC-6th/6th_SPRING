package com.example.umc.study.config.filter;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.AuthHandler;
import com.example.umc.study.config.PrincipalDetails;
import com.example.umc.study.dto.LoginRequestDTO;
import com.example.umc.study.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequestDTO login = readBody(request);


        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                login.getEmail(), login.getPassword()
        );

        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails details = (PrincipalDetails) authResult.getPrincipal();

        String email = details.getUsername();

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        GrantedAuthority authority = iterator.next();

        String role = authority.getAuthority();

        String accessToken = jwtUtil.createAccessToken(email, role);

        response.addHeader("Authorization", "Bearer " + accessToken);
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());

        BaseResponse<Object> baseResponse = BaseResponse.onSuccess(null);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), baseResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ErrorStatus unAuth = ErrorStatus._AUTHENTICATION_FAILED;

        BaseResponse<Object> baseResponse = BaseResponse.onFailure(unAuth.getMessage(), unAuth.getCode(), null);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), baseResponse);
    }

    private LoginRequestDTO readBody(HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(request.getInputStream(), LoginRequestDTO.class);
        } catch (IOException e) {
            throw new AuthHandler(ErrorStatus._BAD_REQUEST);
        }
    }
}
