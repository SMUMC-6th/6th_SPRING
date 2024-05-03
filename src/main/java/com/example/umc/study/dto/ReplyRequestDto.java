package com.example.umc.study.dto;

import lombok.Getter;

public class ReplyRequestDto {
    @Getter
    public static class JoinDto {
        private String title;
        private String content;
    }
}