package com.example.umc.study.converter;


import com.example.umc.study.apiPayload.Role;
import com.example.umc.study.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;



public class AuthConverter {

    public static User toUser(String email, String name, Role role, String password, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .role(String.valueOf(role))
                .password(passwordEncoder.encode(password))
                .name(name)
                .build();
    }
}