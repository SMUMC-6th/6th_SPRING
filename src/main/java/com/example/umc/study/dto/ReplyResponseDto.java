package com.example.umc.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResponseDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinResultDto {

        private String title;
        private String content;
        private Long replyId;
        private LocalDateTime createAt;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReplyPreviewDto {

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
    public static class ReplyPreviewListDto {
        List<ReplyResponseDto.ReplyPreviewDto> replyPreviewDtoList;
    }
}