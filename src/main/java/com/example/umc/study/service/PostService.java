package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;

import java.util.List;

public interface PostService {
    Post createPost(Long userId, PostRequestDTO.JoinDTO joinDTO);

    Post readPost(Long id);

    List<Post> readPosts();

    void deletePost(Long id);

    Post updatePost(Long id, PostRequestDTO.UpdatePostDTO updatePostDTO);

    List<Post> readPostsByUser(Long userId);
}
