package com.example.umc.study.dto;

import lombok.Getter;

public class PostRequestDto {
    @Getter
    public static class CreatePostDto {
        private String title;
        private String content;
    }

    @Getter
    public static class UpdatePostDto {
        private String title;
        private String content;
    }
}
