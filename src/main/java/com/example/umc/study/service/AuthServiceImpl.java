package com.example.umc.study.service;

import com.example.umc.study.config.jwt.JWTUtil;
import com.example.umc.study.converter.AuthConverter;
import com.example.umc.study.domain.Role;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.response.KakaoDTO;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.security.KakaoUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final KakaoUtil kakaoUtil;

    @Override
    public User oAuthLogin(String accessCode, HttpServletResponse httpServletResponse) {

        KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        KakaoDTO.KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);

        Optional<User> queryUser = userRepository.findByEmail(kakaoProfile.getKakao_account().getEmail());

        if (queryUser.isPresent()) {
            // 로그인
            User user = queryUser.get();
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(queryUser.get().getEmail(),
                    "ROLE_USER"));

            return user;

        } else {
            // 회원가입
            User user = AuthConverter.toUser(kakaoProfile.getKakao_account().getEmail(),
                    kakaoProfile.getKakao_account().getProfile().getNickname(),
                    Role.ROLE_USER,
                    "gomin01234",
                    passwordEncoder);

            userRepository.save(user);
            httpServletResponse.setHeader("Authorization", jwtUtil.createAccessToken(user.getEmail(),
                    String.valueOf(user.getRole())));

            return user;
        }
    }
}
