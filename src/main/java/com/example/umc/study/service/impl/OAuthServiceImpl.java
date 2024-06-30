package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.AuthHandler;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.service.OAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthServiceImpl implements OAuthService {
    @Value("${kakao.auth.client}")
    private String client;

    @Value("${kakao.auth.redirect}")
    private String redirect;

    @Override
    public KakaoDTO.KakaoProfile kakaoLogin(String accessCode) {
        KakaoDTO.OAuthToken token = getOAuthToken(accessCode);
        return getKakaoProfile(token);
    }

    @Override
    public KakaoDTO.OAuthToken getOAuthToken(String accessCode) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", client);
        map.add("redirect_uri", redirect);
        map.add("code", accessCode);
        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<String> response1 = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                String.class);

        ObjectMapper om = new ObjectMapper();
        KakaoDTO.OAuthToken token = null;

        try {
            token = om.readValue(response1.getBody(), KakaoDTO.OAuthToken.class);
        } catch (Exception e) {
            throw new AuthHandler(ErrorStatus._AUTH_INVALID_TOKEN);
        }
        return token;
    }

    @Override
    public KakaoDTO.KakaoProfile getKakaoProfile(KakaoDTO.OAuthToken token) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer " + token.getAccess_token());
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap> request1 = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response2 = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request1,
                String.class
        );

        KakaoDTO.KakaoProfile profile = null;
        ObjectMapper om = new ObjectMapper();

        try {
            profile = om.readValue(response2.getBody(), KakaoDTO.KakaoProfile.class);
        } catch(Exception e) {
            e.printStackTrace();
            throw new AuthHandler(ErrorStatus._AUTH_INVALID_TOKEN);
        }
        return profile;
    }
}
