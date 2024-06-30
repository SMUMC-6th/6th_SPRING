package com.example.umc.study.apiPayload.exception.handler;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException, java.io.IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(403);

        BaseResponse<Object> errorResponse =
                BaseResponse.onFailure(
                        ErrorStatus._FORBIDDEN.getCode(),
                        ErrorStatus._FORBIDDEN.getMessage(),
                        null
                );

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);

    }
}