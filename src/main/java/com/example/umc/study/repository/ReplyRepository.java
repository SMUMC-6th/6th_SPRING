package com.example.umc.study.repository;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByPost(Post post);
}
