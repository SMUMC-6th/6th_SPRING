package com.example.umc.study.dto;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReplyResponseDTO {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinReplyResultDTO {
        private Long replyId;
        private LocalDateTime createAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReplyPreviewDTO {
        private Long replyId;
        private String title;
        private String content;
        private LocalDateTime updateAt;
        private LocalDateTime createAt;
    }
}
