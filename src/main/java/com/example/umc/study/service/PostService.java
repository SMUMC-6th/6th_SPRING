package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDto;

import java.util.List;

public interface PostService {
    Post createPost(Long userId, PostRequestDto.CreatePostDto createPostDto);

    Post readPost(Long postId);

    List<Post> readPosts();

    Post updatePost(PostRequestDto.UpdatePostDto updatePostDto, Long postId);

    void deletePost(Long postId);

    List<Post> readPostsByUser(Long userId);
}
