package com.example.umc.study.dto;

import lombok.Getter;

public class PostRequestDTO {

    @Getter
    public static class PostDTO{
        private String title;
        private String content;
    }
}