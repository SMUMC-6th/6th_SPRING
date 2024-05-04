package com.example.umc.study.dto;

import lombok.Getter;

public class PostRequestDTO {

    @Getter
    public static class UploadDTO {

        private String title;

        private String content;
    }

    @Getter
    public static class updatePostDTO {
        private String title;
        private String content;
    }
}
