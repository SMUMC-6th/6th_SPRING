package com.example.umc.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostResultDTO {
        private Long postId;
        private LocalDateTime createAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostPreviewDTO {
        private Long postId;
        private String title;
        private String content;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostPreviewListDTO {
        private List<PostPreviewDTO> postPreviewDTOList;
    }
}
