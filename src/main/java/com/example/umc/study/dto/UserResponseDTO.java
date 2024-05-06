package com.example.umc.study.dto;

import com.example.umc.study.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinResultDTO {
        private Long userId;
        private LocalDateTime createAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserPreviewDTO {
        private Long userId;
        private String name;
        private LocalDateTime updateAt;
        private LocalDateTime createAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserPreviewListDTO {
        List<UserPreviewDTO> userRreviewDTOList;
    }
}
