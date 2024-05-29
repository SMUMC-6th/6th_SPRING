package com.example.umc.study.dto;

import lombok.Getter;

public class ReplyRequestDTO {
    @Getter
    public static class JoinReplyDTO{
        private String title;
        private String content;
    }

    @Getter
    public static class UpdateReplyDTO {
        private String title;
        private String content;
    }
}
