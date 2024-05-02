package com.example.umc.study.converter;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDto;
import com.example.umc.study.dto.UserResponseDto;

import java.util.List;

public class UserConverter {

    public static User toUser(UserRequestDto.JoinDto joinDto) {
        return User.builder()
                .name(joinDto.getName())
                .build();
    }

    public static UserResponseDto.JoinResultDto toJoinResultDto(User user) {
        return UserResponseDto.JoinResultDto.builder()
                .userId(user.getId())
                .createAt(user.getCreatedAt())
                .build();
    }

    public static UserResponseDto.UserPreviewDto toUserPreviewDto(User user) {
        return UserResponseDto.UserPreviewDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .createAt(user.getCreatedAt())
                .updateAt(user.getUpdatedAt())
                .build();
    }

    public static UserResponseDto.UserPreviewListDto toUserPreviewListDto(List<User> userList) {
        List<UserResponseDto.UserPreviewDto> userPreviewDtoList = userList.stream()
                .map(UserConverter::toUserPreviewDto)
                .toList();
        return UserResponseDto.UserPreviewListDto.builder()
                .userPreviewDtoList(userPreviewDtoList)
                .build();
    }
}