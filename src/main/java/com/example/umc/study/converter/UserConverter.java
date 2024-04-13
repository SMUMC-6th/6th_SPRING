package com.example.umc.study.converter;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.dto.UserResponseDTO;

import java.util.List;

public class UserConverter {
    public static User toUser(UserRequestDTO.JoinDTO joinDTO) {
        return User.builder()
                .name(joinDTO.getName())
                .build();
    }

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createAt(user.getCreatedAt())
                .build();
    }

    public static UserResponseDTO.UserPreviewDTO toUserPreviewDTO(User user) {
        return UserResponseDTO.UserPreviewDTO.builder()
                .userId(user.getId())
                .name(user.getName())
                .updateAt(user.getUpdatedAt())
                .createAt(user.getCreatedAt())
                .build();
    }

    public static UserResponseDTO.UserPreviewListDTO toUserPreviewListDTO(List<User> userList) {
        List<UserResponseDTO.UserPreviewDTO> userPreviewDTOList = userList.stream()
                .map(UserConverter::toUserPreviewDTO)
                .toList();
        return UserResponseDTO.UserPreviewListDTO.builder()
                .userRreviewDTOList(userPreviewDTOList)
                .build();
    }
}
