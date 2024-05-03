package com.example.umc.study.service;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.dto.request.UserRequestDTO;

import java.util.List;

public interface PostService {

    Post createPost(Long userId,PostRequestDTO.CreatePostDTO createPostDTO);

    Post readPost(Long postId);
    List<Post> readPostsByUserId(Long userId);

    List<Post> readPosts();

    Post updatePost(PostRequestDTO.UpdatePostDTO updatePostDTO, Long postId);

    void deletePost(Long postId);
}
