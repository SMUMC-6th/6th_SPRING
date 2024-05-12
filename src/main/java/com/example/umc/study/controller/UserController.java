package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.UserRequestDTO;
import com.example.umc.study.dto.response.UserResponseDTO;
import com.example.umc.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public BaseResponse<UserResponseDTO.JoinResultDTO> createUser(@RequestBody UserRequestDTO.JoinDTO joinDTO) {
        User user = userService.createUser(joinDTO);
        return BaseResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/users/{userId}")
    public BaseResponse<UserResponseDTO.UserPreviewDTO> readUser(@PathVariable Long userId) {
        User user =  userService.readUser(userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDTO(user));
    }

    @GetMapping("/users")
    public BaseResponse<UserResponseDTO.UserPreviewListDTO> readUsers() {
        List<User> userList = userService.readUsers();
        return BaseResponse.onSuccess(UserConverter.toUserPreviewListDTO(userList));
    }

    @DeleteMapping("/users/{userId}")
    public BaseResponse<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return BaseResponse.onSuccess("삭제에 성공하였습니다.");
    }

    @PatchMapping("/users/{userId}")
    public BaseResponse<UserResponseDTO.UserPreviewDTO> updateUser(@RequestBody UserRequestDTO.UpdateUserDTO updateUserDTO, @PathVariable Long userId) {
        User user = userService.updateUser(updateUserDTO, userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDTO(user));
    }
}
