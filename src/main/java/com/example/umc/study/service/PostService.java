package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDto;

import java.util.List;

public interface PostService {
    Post createPost(PostRequestDto.JoinDto joinDto);

    Post readPost(Long postId);

    List<Post> readPosts();

    void deletePost(Long postId);
}
