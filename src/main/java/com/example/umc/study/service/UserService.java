package com.example.umc.study.service;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDTO;
import jakarta.transaction.Transactional;

public interface UserService {
    User createUser(UserRequestDTO.JoinDTO joinDTO);

    User readUser(Long userId);

    void deleteUser(Long userId);

}
