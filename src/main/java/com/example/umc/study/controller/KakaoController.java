package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.AuthHandler;
import com.example.umc.study.config.jwt.JWTUtil;
import com.example.umc.study.converter.AuthConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.domain.enums.Role;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.dto.KakaoProfile;
import com.example.umc.study.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RequiredArgsConstructor
public class KakaoController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final User user;

    @Value("${spring.kakao.auth.client}")
    private String client;
    @Value("${spring.kakao.auth.redirect}")
    private String redirect;

    @GetMapping("auth/login/kakao")
    public BaseResponse<Object> KakaoLogin(@RequestParam("code") String accessCode, HttpServletResponse httpServletResponse) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded);charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client);
        params.add("redirect_url", redirect);
        params.add("code", accessCode);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "https://kauth.kakao.com/oauth/token",
                        HttpMethod.POST,
                        kakaoTokenRequest,
                        String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        KakaoDTO.OAuthToken oAuthToken = null;

        try {
            oAuthToken = objectMapper.readValue(response.getBody(), KakaoDTO.OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new AuthHandler(ErrorStatus._INVALID_REQUEST_CODE);
        }

        System.out.println(oAuthToken.getAccess_token());

        //2

        RestTemplate restTemplate2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded);charset=utf-8");
        headers.add("Authorization", "Bearer " + oAuthToken.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> KakaoProfileRequest = new HttpEntity <>(headers2);

        ResponseEntity<String> response2 =
                restTemplate.exchange(
                        "https://kapi.kakao.com/v2/user/me",
                        HttpMethod.GET,
                        kakaoTokenRequest,
                        String.class);
        System.out.println(response2);


        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new AuthHandler(ErrorStatus._INVALID_REQUEST_CODE);
        }

        System.out.println(kakaoProfile.getKakaoAccount().getEmail());

        if(userRepository.existsByEmail(kakaoProfile.getKakaoAccount().getEmail())) {
            //로그인
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(kakaoProfile.getKakaoAccount().getEmail(), "ROLE_USER"));
            return BaseResponse.onSuccess(null);

        } else {
            //회원가입
            User user = AuthConverter.toUser(kakaoProfile.getKakaoAccount().getEmail(), kakaoProfile.getKakaoAccount().getProfile().getNickname(), Role.ROLE_USER, "jamey1234", passwordEncoder);
            userRepository.save(user);
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(user.getEmail(), String.valueOf(user.getRole())));
            return BaseResponse.onSuccess(null);


        }



    }
}
