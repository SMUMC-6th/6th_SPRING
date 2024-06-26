package com.example.umc.study.service;

import com.example.umc.study.dto.KakaoDTO;

public interface OAuthService {
    KakaoDTO.KakaoProfile kakaoLogin(String accessCode);
    KakaoDTO.OAuthToken getOAuthToken(String accessCode);
    KakaoDTO.KakaoProfile getKakaoProfile(KakaoDTO.OAuthToken token);
}
