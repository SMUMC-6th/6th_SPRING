package com.example.umc.study.service;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.UserRequestDTO;

import java.util.List;

public interface UserService {

    User createUser(UserRequestDTO.JoinDTO joinDTO);


    User readUser(Long userId);

    List<User> readUsers();

    void deleteUser(Long userId);

    User updateUser(UserRequestDTO.UpdateUserDTO updateUserDTO, Long userId);
}
