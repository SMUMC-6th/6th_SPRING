package com.example.umc.study.dto;

import lombok.Getter;

public class UserRequestDto {
    @Getter
    public static class JoinDto {
        private String name;
        private String email;
        private String password;
    }

    @Getter
    public static class UpdateUserDto {
        private String name;
    }
}