package com.example.umc.study.repository;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}