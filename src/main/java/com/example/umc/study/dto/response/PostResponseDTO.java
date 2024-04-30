package com.example.umc.study.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public class PostResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreatePostResultDTO{
        private Long postId;
        private LocalDateTime createAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostPreviewDTO{
        private Long postId;
        private String title;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }
}
