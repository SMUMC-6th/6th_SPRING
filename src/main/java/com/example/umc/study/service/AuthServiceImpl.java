package com.example.umc.study.service;


import com.example.umc.study.apiPayload.KakaoUtil;
import com.example.umc.study.apiPayload.Role;
import com.example.umc.study.config.jwt.JWTUtil;
import com.example.umc.study.converter.AuthConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.dto.KakaoProfile;
import com.example.umc.study.repository.UserRepository;
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
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User oAuthLogin(String accessCode, HttpServletResponse httpServletResponse) {
        KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);

        Optional<User> queryUser = userRepository.findByEmail(kakaoProfile.getKakaoAccount().getEmail());

        if (queryUser.isPresent()) {
            User user = queryUser.get();
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(user.getEmail(), "ROLE_USER"));
            return user;
        } else {
            User user = AuthConverter.toUser(kakaoProfile.getKakaoAccount().getEmail(),
                    kakaoProfile.getKakaoAccount().getProfile().getNickname(),
                    Role.ROLE_USER, "1234", passwordEncoder);
            userRepository.save(user);
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(user.getEmail(), String.valueOf(user.getRole())));
            return user;
        }
    }
}
