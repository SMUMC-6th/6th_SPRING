package com.example.umc.study.repository;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
