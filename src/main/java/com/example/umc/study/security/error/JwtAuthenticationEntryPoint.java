package com.example.umc.study.security.error;


import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(401);

        BaseResponse<Object> errorResponse =
                BaseResponse.onFailure(
                        ErrorStatus._UNAUTHORIZED.getCode(),
                        ErrorStatus._UNAUTHORIZED.getMessage(),
                        null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
