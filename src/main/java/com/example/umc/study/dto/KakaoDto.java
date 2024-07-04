package com.example.umc.study.dto;

public record KakaoDto(
) {
    public record OAuthToken(
            String access_token,
            String token_type,
            String refresh_token,
            Integer expires_in,
            String scope,
            Integer refresh_token_expires_in
    ) {}
}
