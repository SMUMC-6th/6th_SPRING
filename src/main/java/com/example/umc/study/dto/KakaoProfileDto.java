package com.example.umc.study.dto;

public record KakaoProfileDto(
        Long id,
        String connected_at,
        Properties properties,
        KakaoAccount kakao_account
) {
    public record Properties (
        String nickname
    ) {}

    public record KakaoAccount (
            String email,
            Boolean is_email_verified,
            Boolean has_email,
            Boolean profile_nickname_needs_agreement,
            Boolean email_needs_agreement,
            Boolean is_email_valid,
            Profile profile
    ) {}

    public record Profile (
            String nickname,
            Boolean is_default_nickname
    ) {}
}
