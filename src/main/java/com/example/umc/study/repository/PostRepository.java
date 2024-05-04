package com.example.umc.study.repository;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //User로 모든것을 찾겠다는 의미
    List<Post> findAllByUser(User user);

}
