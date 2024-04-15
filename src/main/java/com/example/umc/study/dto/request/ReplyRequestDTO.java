package com.example.umc.study.dto.request;

import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {
        private String title;
        private String content;
    }
}
