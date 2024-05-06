package com.example.umc.study.dto;

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
    public static class CreateReplyResultDTO{ // Reply 정보
        private Long replyId;
        private LocalDateTime createAt;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReplyPreviewDTO{
        private Long replyId;
        private String title;
        private String content;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReplyPreviewListDTO{
        List<ReplyResponseDTO.ReplyPreviewDTO> replyPreviewDTOList;
    }
}
