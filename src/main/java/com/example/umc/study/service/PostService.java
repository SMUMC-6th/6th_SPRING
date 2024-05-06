package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.UserRequestDTO;

import java.util.List;

public interface PostService {
    Post createPost(Long userId, PostRequestDTO.JoinPostDTO joinPostDTO);

    Post readPost(Long postId);

    void deletePost(Long postId);

    List<Post> readPosts();

    Post updatePost(PostRequestDTO.UpdatePostDTO updatePostDTO, Long postId);

    List<Post> readPostsByUser(Long userId);
}
