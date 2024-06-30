package com.example.umc.study.controller;

import com.example.umc.study.apiPayLoad.BaseResponse;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDto;
import com.example.umc.study.dto.UserResponseDto;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.UserService;
import com.example.umc.study.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.kakao.auth.client}")
    private String client;

    @Value("${spring.kakao.auth.redirect}")
    private String redirect;

    @PostMapping("users")
    public BaseResponse<UserResponseDto.JoinResultDto> createUser(
            @RequestBody UserRequestDto.JoinDto joinDto
            ) {
        User user = userService.createUser(joinDto);
        return BaseResponse.onSuccess(UserConverter.toJoinResultDto(user));
    }

    @GetMapping("users/{userId}")
    public BaseResponse<UserResponseDto.UserPreviewDto> readUser(
            @PathVariable Long userId) {
        User user = userService.readUser(userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDto(user));
    }

    @GetMapping("users")
    public BaseResponse<UserResponseDto.UserPreviewListDto> readUsers() {
        List<User> userList = userService.readUsers();
        return BaseResponse.onSuccess(UserConverter.toUserPreviewListDto(userList));
    }

    @PatchMapping("users/{userId}")
    public BaseResponse<UserResponseDto.UserPreviewDto> updateUser(@RequestBody UserRequestDto.UpdateUserDto updateUserDto,
                                                                   @PathVariable Long userId) {
        User user = userService.updateUser(updateUserDto, userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDto(user));
    }

    @DeleteMapping("users/{userId}")
    public BaseResponse<String> deleteUser(
            @PathVariable Long userId) {
        userService.deleteUser(userId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }
}
