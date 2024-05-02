package com.example.umc.study.service;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDto;

import java.util.List;

public interface UserService {

    User createUser(UserRequestDto.JoinDto joinDTO);
    User readUser(Long userId);
    List<User> readUsers();
    void deleteUser(Long userId);
}