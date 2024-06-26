package com.example.umc.study.service;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.KakaoDTO;
import com.example.umc.study.dto.UserRequestDTO;

import java.util.List;

public interface UserService {

    User createUser(UserRequestDTO.JoinDTO joinDTO);

    User createUser(KakaoDTO.KakaoProfile profile);

    User readUser(Long userId);

    List<User> readUsers();

    void deleteUser(Long userId);

    User updateUser(UserRequestDTO.UpdateUserDTO updateUserDTO, Long userId);

    boolean isExistByEmail(String email);
}
