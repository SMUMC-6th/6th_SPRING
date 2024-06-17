package com.example.umc.study.controller;


import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.response.UserResponseDTO;
import com.example.umc.study.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/auth/login/kakao")
    public BaseResponse<UserResponseDTO.JoinResultDTO> kakaoLogin(@RequestParam("code") String accessCode, HttpServletResponse httpServletResponse) {
        User user = authService.oAuthLogin(accessCode, httpServletResponse);
        return BaseResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

}
