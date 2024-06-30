package com.example.umc.study.security;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.AuthHandler;
import com.example.umc.study.dto.KakaoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class KakaoUtil {

    @Value("${spring.kakao.auth.client}")
    private String client;
    @Value("${spring.kakao.auth.redirect}")
    private String redirect;

    public KakaoDTO.OAuthToken requestToken(String accessCode) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client);
        params.add("redirect_uri", redirect);
        params.add("code", accessCode);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoTokenRequest,
                    String.class
            );
            System.out.println("Kakao API response: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Failed to send request to Kakao API");
            throw new AuthHandler(ErrorStatus._INVALID_REQUEST_CODE);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoDTO.OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), KakaoDTO.OAuthToken.class);
            System.out.println("Access Token: " + oAuthToken.getAccess_token());
        } catch (JsonProcessingException e) {
            System.out.println("Failed to parse Kakao API response");
            throw new AuthHandler(ErrorStatus._INVALID_REQUEST_CODE);
        }

        return oAuthToken;
    }



    public KakaoDTO.KakaoProfile requestProfile(KakaoDTO.OAuthToken oAuthToken) {
        System.out.println("requestProfile 메소드 시작");
        System.out.println(oAuthToken.getAccess_token());
        RestTemplate restTemplate2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();

        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = restTemplate2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                kakaoProfileRequest,
                String.class
        );
        System.out.println("response2: " + response2.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoDTO.KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper.readValue(response2.getBody(), KakaoDTO.KakaoProfile.class);
            System.out.println("Email: " + kakaoProfile.getKakao_account().getEmail());
        } catch (JsonProcessingException e) {
            System.out.println("////////////////////////"+Arrays.toString(e.getStackTrace()));
            System.out.println("response2 error");
            throw new AuthHandler(ErrorStatus._INVALID_REQUEST_CODE);
        }
        System.out.println("///////////////////////"+kakaoProfile.getKakao_account().getEmail());

        return kakaoProfile;
    }
}
