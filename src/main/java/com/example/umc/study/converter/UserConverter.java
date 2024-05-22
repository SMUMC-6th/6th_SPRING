package com.example.umc.study.converter;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.dto.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserConverter {
    //User를 빌더패턴으로
    //Converter안의 메소드 명은 to(리턴시켜주는 것)

    public static User toUser(UserRequestDTO.JoinDTO joinDTO, PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(joinDTO.getName())
                .password(passwordEncoder.encode(joinDTO.getPassword()))
                .email(joinDTO.getEmail())
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
        List<UserResponseDTO.UserPreviewDTO> userPreviewDTOList = userList.stream().map(UserConverter::toUserPreviewDTO).toList();

        return UserResponseDTO.UserPreviewListDTO.builder().userPreviewDTOList(userPreviewDTOList).build();

    }
}
