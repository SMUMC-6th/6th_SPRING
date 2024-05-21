package com.example.umc.study.dto.request;


import lombok.Getter;


public class UserRequestDTO {

    @Getter
    public static class JoinDTO {
        private String name;
        private String email;
        private String password;
    }

    @Getter
    public static class UpdateUserDTO {
        private String name;
    }
}
