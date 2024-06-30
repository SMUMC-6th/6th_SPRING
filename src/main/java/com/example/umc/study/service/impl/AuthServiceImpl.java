package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.config.jwt.JWTUtil;
import com.example.umc.study.converter.AuthConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.domain.enums.Role;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.dto.response.UserResponseDTO;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.security.KakaoUtil;
import com.example.umc.study.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KakaoUtil kakaoUtil;
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User oAuthLogin(String accessCode, HttpServletResponse httpServletResponse) {
        KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        KakaoDTO.KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);

        Optional<User> queryUser = userRepository.findByEmail(kakaoProfile.getKakao_account().getEmail());

        if (queryUser.isPresent()) {
            User user = queryUser.get();
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(queryUser.get().getEmail(), "ROLE_USER"));
            return user;
        } else {
            User user = AuthConverter.toUser(kakaoProfile.getKakao_account().getEmail(),
                    kakaoProfile.getKakao_account().getProfile().getNickname(),
                    Role.ROLE_USER,
                    "password",
                    passwordEncoder);
            userRepository.save(user);
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(user.getEmail(), String.valueOf(user.getRole())));
            return user;
        }
    }
}
