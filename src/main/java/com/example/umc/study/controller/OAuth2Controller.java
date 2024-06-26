package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.service.OAuthService;
import com.example.umc.study.service.UserService;
import com.example.umc.study.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final JwtUtil jwtUtil;

    @GetMapping("/auth/login/kakao")
    public BaseResponse<Void> loginKakao(HttpServletResponse response, @RequestParam(name = "code") String accessCode) {

        KakaoDTO.KakaoProfile profile = oAuthService.kakaoLogin(accessCode);

        // email 접근이 안돼서 nickname + id로 했습니다.
        if (!userService.isExistByEmail(profile.getKakao_account().getProfile().getNickname() + profile.getId())) {
            userService.createUser(profile);
        }
        else {
            response.addHeader("Authorization", "Bearer " + jwtUtil.createAccessToken(profile.getKakao_account().getProfile().getNickname() + profile.getId(), "ROLE_USER"));
        }
        return BaseResponse.onSuccess(null);

    }
}
