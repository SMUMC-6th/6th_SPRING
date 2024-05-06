package com.example.umc.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateReplyResultDTO{
        private Long replyId;
        private Long postId;
        private Long userId;
        private LocalDateTime createAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReplyPreviewDTO{
        private Long replyId;
        private String content;
        private Long postId;
        private Long userId;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReplyPreviewListDTO{
        List<ReplyPreviewDTO> replyPreviewDTOList;
    }

}
