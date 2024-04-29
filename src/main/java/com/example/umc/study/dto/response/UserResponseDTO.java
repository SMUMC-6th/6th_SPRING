package com.example.umc.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserResponseDTO {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinResultDTO{
        private Long userId;
        private LocalDateTime createdAt;
    }
}
