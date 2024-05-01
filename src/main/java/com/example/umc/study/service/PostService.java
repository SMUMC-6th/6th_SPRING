package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.UserRequestDTO;

import java.util.List;

public interface PostService {
    Post createPost(PostRequestDTO.JoinPostDTO joinPostDTO);

    Post readPost(Long postId);

    void deletePost(Long postId);

    List<Post> readPosts();
}
