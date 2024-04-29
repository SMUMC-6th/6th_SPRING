package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.service.UserService;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.UserRequestDTO;
import com.example.umc.study.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public BaseResponse<UserResponseDTO.JoinResultDTO> createUser(@RequestBody UserRequestDTO.JoinDTO joinDTO) {
        User user = userService.createUser(joinDTO);
        return BaseResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

}
