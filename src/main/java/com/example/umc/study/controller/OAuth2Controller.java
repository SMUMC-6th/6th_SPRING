package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.config.jwt.JWTUtil;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.service.OAuthService;
import com.example.umc.study.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    @Value("${kakao.auth.client}")
    private String client;

    @Value("${kakao.auth.redirect}")
    private String redirect;

    private final OAuthService oAuthService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @GetMapping("/auth/login/kakao")
    public BaseResponse<Void> loginKakao(HttpServletResponse response, @RequestParam(name = "code") String accessCode) {

        KakaoDTO.KakaoProfile profile = oAuthService.kakaoLogin(accessCode);

        if (!userService.isExistByEmail(profile.getKakao_account().getEmail())) {
            userService.createUser(profile);
        }
        else {
            response.addHeader("Authorization", "Bearer " + jwtUtil.createAccessToken(profile.getKakao_account().getEmail(), "ROLE_USER"));
        }
        return BaseResponse.onSuccess(null);

    }
}