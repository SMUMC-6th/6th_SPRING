package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.dto.UserResponseDTO;
import com.example.umc.study.service.PostService;
import com.example.umc.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
<<<<<<< Updated upstream
=======
@RequestMapping("/api/v1")
@CrossOrigin("*")
>>>>>>> Stashed changes
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @PostMapping("/api/v1/users")
    public BaseResponse<UserResponseDTO.JoinResultDTO> createUser(@RequestBody UserRequestDTO.JoinDTO joinDTO) {
        User user = userService.createUser(joinDTO);
        return BaseResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/api/v1/users/{userId}")
    public BaseResponse<UserResponseDTO.UserPreviewDTO> readUser(@PathVariable("userId") Long userId) {
        User user = userService.readUser(userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDTO(user));
    }

    @GetMapping("/api/v1/users")
    public BaseResponse<UserResponseDTO.UserPreviewListDTO> readUsers() {
        List<User> userList = userService.readUsers();
        return BaseResponse.onSuccess(UserConverter.toUserPreviewListDTO(userList));
    }

    @DeleteMapping("/api/v1/users/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PatchMapping("/users/{userId}")
    public BaseResponse<UserResponseDTO.UserPreviewDTO> updateUser(@RequestBody UserRequestDTO.UpdateUserDTO updateUserDTO, @PathVariable("userId") Long userId) {
        User user = userService.updateUser(updateUserDTO, userId);
        return BaseResponse.onSuccess(UserConverter.toUserPreviewDTO(user));
    }



}
