package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;

import java.util.List;

public interface PostService {

    Post createPost(PostRequestDTO.CreatePostDTO createPostDTO, Long userId);

    Post readPost(Long postId);

    List<Post> readPosts();

    void deletePost(Long postId);

    Post updatePost(PostRequestDTO.UpdatePostDTO updatePostDTO, Long postId);

    List<Post> readPostsByUser(Long userId);
}
