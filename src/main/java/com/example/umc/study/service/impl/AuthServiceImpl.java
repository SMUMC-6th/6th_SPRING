package com.example.umc.study.service.impl;

import com.example.umc.study.auth.util.JwtUtil;
import com.example.umc.study.auth.util.KakaoUtil;
import com.example.umc.study.converter.AuthConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final KakaoUtil kakaoUtil;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User oAuthLogin(String accessCode, HttpServletResponse httpServletResponse) {
        KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        KakaoDTO.KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);

        Optional<User> queryUser = userRepository.findByEmail(kakaoProfile.getKakao_account().getEmail());

        if (queryUser.isPresent()) {
            User user = queryUser.get();
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(user.getEmail(), "ROLE_USER"));
            return user;
        } else {
            User user = AuthConverter.toUser(kakaoProfile.getKakao_account().getEmail(),
                    kakaoProfile.getKakao_account().getProfile().getNickname(),
                    "1234",
                    passwordEncoder);
            userRepository.save(user);
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(user.getEmail(), String.valueOf(user.getRole())));
            return user;
        }
    }
}