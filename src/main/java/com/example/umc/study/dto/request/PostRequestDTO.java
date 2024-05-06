package com.example.umc.study.dto.request;

import lombok.Getter;

public class PostRequestDTO {

    @Getter
    public static class CreatePostDTO {
        private String title;
        private String content;
    }

    @Getter
    public static class UpdatePostDTO {
        private String title;
        private String content;
    }
}
