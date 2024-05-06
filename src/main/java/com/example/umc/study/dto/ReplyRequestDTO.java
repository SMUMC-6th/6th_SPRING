package com.example.umc.study.dto;

import lombok.Getter;

public class ReplyRequestDTO {
    @Getter
    public static class ReplyDTO {
        private String title;
        private String content;
    }
}
