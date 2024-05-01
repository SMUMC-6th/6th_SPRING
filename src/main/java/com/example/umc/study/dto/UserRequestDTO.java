package com.example.umc.study.dto;

import lombok.Getter;

public class UserRequestDTO {
    @Getter
    public static class JoinDTO {
        private String name; //User 엔티티에 name만 받으면 되기 때문
    }

}
