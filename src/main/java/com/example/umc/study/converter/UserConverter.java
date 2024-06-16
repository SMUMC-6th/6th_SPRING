package com.example.umc.study.converter;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.UserRequestDTO;
import com.example.umc.study.dto.response.UserResponseDTO;
import com.example.umc.study.domain.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserConverter {


    public static User toUser(UserRequestDTO.JoinDTO joinDTO, PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(joinDTO.getName())
                .password(passwordEncoder.encode((joinDTO).getPassword()))
                .email(joinDTO.getEmail())
                .role(Role.valueOf(joinDTO.getRole()))
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
                .userPreviewDTOList(userPreviewDTOList)
                .build();
    }
}
