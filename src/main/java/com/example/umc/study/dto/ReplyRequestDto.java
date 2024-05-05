package com.example.umc.study.dto;

import lombok.Getter;

public class ReplyRequestDto {
    @Getter
    public static class CreateReplyDto {
        private String title;
        private String content;
    }
}


