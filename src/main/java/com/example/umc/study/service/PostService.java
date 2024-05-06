package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDto;

import java.util.List;

public interface PostService {
    Post createPost(Long joinDto, PostRequestDto.CreatePostDto userId);

    Post readPost(Long postId);

    List<Post> readPosts();

    Post updatePost(PostRequestDto.UpdatePostDTO updatePostDTO, Long postId);

    List<Post> readPostsByUser(Long userId);

    void deletePost(Long postId);
}