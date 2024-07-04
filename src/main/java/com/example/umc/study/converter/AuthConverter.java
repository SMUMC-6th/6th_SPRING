package com.example.umc.study.converter;

import com.example.umc.study.domain.User;
import com.example.umc.study.domain.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthConverter {

    public static User toUser(String email, String name, Role role, String password, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .role(role)
                .password(passwordEncoder.encode(password))
                .name(name)
                .build();
    }
}
