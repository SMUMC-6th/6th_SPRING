package com.example.umc.study.dto;

import com.example.umc.study.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PrimitiveIterator;

public class PostResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UploadResultDTO {  //리턴하고 싶은 데이터를 적어주는 보통은 save해주고 id 많이 리턴함

        private Long postId;
        private LocalDateTime createAt;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PostPreviewDTO {
        private Long postId;
        private String title;
        private String content;
        private LocalDateTime updateAt;
        private LocalDateTime createAt;

    }

    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post) {
        return PostPreviewDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .updateAt(post.getUpdatedAt())
                .createAt(post.getCreatedAt())
                .build();
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostPreviewListDTO {
        List<PostPreviewDTO> postPreviewList;
    }
}
