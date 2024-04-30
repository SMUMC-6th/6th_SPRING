package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;

public interface PostService {

    Post createPost(Long userId,PostRequestDTO.CreatePostDTO createPostDTO);
}
