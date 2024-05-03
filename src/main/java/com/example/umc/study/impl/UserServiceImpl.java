package com.example.umc.study.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.UserRequestDTO;
import com.example.umc.study.handler.UserHandler;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequestDTO.JoinDTO joinDTO) {
        User user = UserConverter.toUser(joinDTO);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User readUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus._USER_NOT_FOUND));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> readUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus._USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public User updateUser(UserRequestDTO.UpdateUserDTO updateUserDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus._USER_NOT_FOUND));
        user.update(updateUserDTO.getName());
        return user;
    }
}
