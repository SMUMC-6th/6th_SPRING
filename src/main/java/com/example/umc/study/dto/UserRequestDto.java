package com.example.umc.study.dto;

import lombok.Getter;

public class UserRequestDto {
    @Getter
    public static class JoinDto {
        private String name;
    }

    @Getter
    public static class UpdateUserDTO {
        private String name;
    }
}