package com.example.umc.study.service;

import com.example.umc.study.domain.User;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    User oAuthLogin(String accessCode, HttpServletResponse httpServletResponse);
}