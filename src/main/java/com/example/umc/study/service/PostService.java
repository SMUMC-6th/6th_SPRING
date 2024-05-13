package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.dto.request.UserRequestDTO;

import java.util.List;

public interface PostService {
    Post createPost(Long userId, PostRequestDTO.CreatePostDTO createPostDTO);

    Post readPost(Long postId);

    List<Post> readPosts();

    void deletePost(Long postId);

    List<Post> readPostsByUser(Long userId);

    Post updatePost(PostRequestDTO.UpdatePostDTO updatePostDTO, Long postId);
}
