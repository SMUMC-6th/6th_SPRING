package com.example.umc.study.service.impl;

import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.UserRequestDTO;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequestDTO.JoinDTO joinDTO){
        User user = UserConverter.toUser(joinDTO);
        return userRepository.save(user);
    }
}
