package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.UserRequestDTO;

public interface PostService {
    Post createPost(PostRequestDTO.JoinPostDTO joinPostDTO);

    Post readPost(Long postId);
}
