package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.dto.UserResponseDTO;
import com.example.umc.study.service.UserService;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/api/v1/users")
    public BaseResponse<UserResponseDTO.JoinResultDTO> createUser(@RequestBody UserRequestDTO.JoinDTO joinDTO) {
        User user = userService.createUser(joinDTO);
        return BaseResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/api/v1/users/{userId}")
    public BaseResponse<UserResponseDTO.UserPreviewDTO> readUser(@PathVariable Long userId) {
        User user = userService.readUser(userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDTO(user));
    }

    @GetMapping("/api/v1/users")
    public BaseResponse<UserResponseDTO.UserPreviewListDTO> readUsers() {
        List<User> userList =  userService.readUsers();
        return BaseResponse.onSuccess(UserConverter.toUserPreviewListDTO(userList));
    }
    @DeleteMapping("/api/v1/users/{userId}")
    public BaseResponse<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }
}
