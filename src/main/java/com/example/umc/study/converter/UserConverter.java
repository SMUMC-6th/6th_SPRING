package com.example.umc.study.converter;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.dto.UserResponseDTO;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


public class UserConverter {

    public static User toUser(UserRequestDTO.JoinDTO joinDTO, PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(joinDTO.getName())
                .password(passwordEncoder.encode(joinDTO.getPassword()))
                .email(joinDTO.getEmail())
                .role(joinDTO.getRole())
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
                .UserPreviewDTOList(userPreviewDTOList)
                .build();
    }

    public static User toUser(KakaoDTO.KakaoProfile profile, String password, PasswordEncoder encoder) {
        return User.builder()
                .email(profile.getKakao_account().getEmail())
                .name(profile.getKakao_account().getProfile().getNickname())
                .role("ROLE_USER")
                .password(encoder.encode(password))
                .build();
    }

}
