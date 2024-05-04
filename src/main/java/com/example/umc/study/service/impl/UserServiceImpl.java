package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayLoad.code.status.ErrorStatus;
import com.example.umc.study.apiPayLoad.exception.handler.UserHandler;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.UserRequestDto;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequestDto.JoinDto joinDto) {
        User user = UserConverter.toUser(joinDto);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User readUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> readUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UserRequestDto.UpdateUserDto updateUserDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        user.update(updateUserDto.getName());
        return user;
    }
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        userRepository.delete(user);
    }
}
