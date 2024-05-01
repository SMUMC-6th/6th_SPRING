package com.example.umc.study.dto;

import lombok.Getter;

public class PostRequestDTO {
    @Getter
    public static class JoinPostDTO{
        private String title;
        private String content;
    }
}