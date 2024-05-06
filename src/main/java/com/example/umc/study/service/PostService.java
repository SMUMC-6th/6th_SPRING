package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;

import java.util.List;

public interface PostService {
    Post createPost(Long userId, PostRequestDTO.PostDTO postDTO);

    Post readPost(Long postId);

    List<Post> readPosts();

    void deletePost(Long postId);

    Post updatePost(PostRequestDTO.UpdatePostDTO updatepostDTO, Long postId);

    List<Post> readPostsByUser(Long userId);
}
