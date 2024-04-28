package com.example.umc.study.dto;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreatePostResultDTO{ // post 만든 정보
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
        private String content;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostPreviewListDTO {
        List<PostResponseDTO.PostPreviewDTO> postPreviewDTOList;
    }


}
