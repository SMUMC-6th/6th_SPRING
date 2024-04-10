package com.example.umc.study.controller;

import com.example.umc.study.apiPayLoad.BaseResponse;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDto;
import com.example.umc.study.dto.UserResponseDto;
import com.example.umc.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public BaseResponse<UserResponseDto.JoinResultDto> createUser(
            @RequestBody UserRequestDto.JoinDto joinDto
            ) {
        User user = userService.createUser(joinDto);
        return BaseResponse.onSuccess(UserConverter.toJoinResultDto(user));
    }

    @GetMapping("/api/v1/users/{userId}")
    public BaseResponse<UserResponseDto.UserPreviewDto> readUser(
            @PathVariable Long userId) {
        User user = userService.readUser(userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDto(user));
    }

    @GetMapping("/api/v1/users")
    public BaseResponse<UserResponseDto.UserPreviewListDto> readUsers() {
        List<User> userList = userService.readUsers();
        return BaseResponse.onSuccess(UserConverter.toUserPreviewListDto(userList));
    }

    @DeleteMapping("/api/v1/users/{userId}")
    public void deleteUser(
            @PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
