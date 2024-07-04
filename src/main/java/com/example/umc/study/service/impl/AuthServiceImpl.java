package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayLoad.code.status.ErrorStatus;
import com.example.umc.study.apiPayLoad.exception.handler.UserHandler;
import com.example.umc.study.converter.AuthConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.domain.enums.Role;
import com.example.umc.study.dto.KakaoDto;
import com.example.umc.study.dto.KakaoProfileDto;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.AuthService;
import com.example.umc.study.util.JwtUtil;
import com.example.umc.study.util.KakaoUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final KakaoUtil kakaoUtil;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User oAuthLogin(String accessCode, HttpServletResponse httpServletResponse) {
        KakaoDto.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        log.info(oAuthToken.toString());
        KakaoProfileDto kakaoProfile = kakaoUtil.requestProfile(oAuthToken);

        Optional<User> queryUser = userRepository.findByEmail(kakaoProfile.kakao_account().email());

        if (queryUser.isPresent()) {
            User user = queryUser.get();
            httpServletResponse.setHeader("Authorization",
                    jwtUtil.createAccessToken(user.getEmail(), "ROLE_USER"));
            return user;
        } else {
            User user = AuthConverter.toUser(kakaoProfile.kakao_account().email(),
                    kakaoProfile.kakao_account().profile().nickname(),
                    Role.ROLE_USER, "12345", passwordEncoder);
            userRepository.save(user);
            httpServletResponse.setHeader("Authorization",
                    jwtUtil.createAccessToken(user.getEmail(), String.valueOf(user.getRole())));
            return user;
        }
    }
}
