package com.example.umc.study.converter;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.UserRequestDTO;
import com.example.umc.study.dto.response.UserResponseDTO;


public class UserConverter {
    public static User toUser(UserRequestDTO.JoinDTO joinDTO) {
        return User.builder()
                .name(joinDTO.getName())
                .build();
    }
    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
