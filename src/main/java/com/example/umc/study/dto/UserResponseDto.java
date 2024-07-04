package com.example.umc.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinResultDto {

        private Long userId;
        private LocalDateTime createAt;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserPreviewDto {

        private Long userId;
        private String name;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserPreviewListDto {
        List<UserPreviewDto> userPreviewDtoList;
    }
}
