package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.dto.UserResponseDTO;
import com.example.umc.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    //회원 등록
    @PostMapping("/users")
    public BaseResponse<UserResponseDTO.JoinResultDTO> createUser(@RequestBody UserRequestDTO.JoinDTO joinDTO) {
        User user = userService.createUser(joinDTO);
        return BaseResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    //회원 id로 조회
    @GetMapping("/users/{userId}")
    public BaseResponse<UserResponseDTO.UserPreviewDTO> readUser(@PathVariable Long userId) {
        User user = userService.readUser(userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDTO(user));
    }

    //회원 전체조회
    @GetMapping("/users")
    public BaseResponse<UserResponseDTO.UserPreviewListDTO> readUsers() {
        List<User> userList = userService.readUsers();
        return BaseResponse.onSuccess(UserConverter.toUserPreviewListDTO(userList));
    }

    //회원 id로 삭제
    @DeleteMapping("/users/{userId}")
    public BaseResponse<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }

    //회원 정보 수정(이름)
    @PatchMapping("/users/{userId}")
    public BaseResponse<UserResponseDTO.UserPreviewDTO> updateUser(@RequestBody UserRequestDTO.UpdateUserDTO updateUserDTO,
                                                                   @PathVariable Long userId) {
        User user = userService.updateUser(updateUserDTO, userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDTO(user));
    }
}
