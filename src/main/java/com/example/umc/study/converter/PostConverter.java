package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.dto.response.PostResponseDTO;

public class PostConverter {

    public static Post toPost(PostRequestDTO.CreatePostDTO createPostDTO){
        return Post.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .build();
    }

    public static PostResponseDTO.CreatePostResultDTO toCreatePostResultDTO(Post post){
        return PostResponseDTO.CreatePostResultDTO.builder()
                .postId(post.getId())
                .createAt(post.getCreatedAt())
                .build();
    }
}
