package com.example.umc.study.service;

import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.UserRequestDTO;

public interface UserService {
    User createUser(UserRequestDTO.JoinDTO joinDTO);
}
