package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.PostRequestDTO;

import java.util.List;

public interface PostService {

    Post createPost(PostRequestDTO.UploadDTO uploadDTO, Long userId);

    Post readPost(Long postId);

    List<Post> readPosts();

    void deletePost(Long postId);

    Post updatePost(PostRequestDTO.updatePostDTO updatePostDTO, Long postId);

    List<Post> readPostByUser(Long userId);

}
