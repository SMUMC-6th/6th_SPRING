package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;

public class PostConverter {

    public static Post toPost(PostRequestDTO.JoinPostDTO joinPostDTO) {
        return Post.builder()
                .title(joinPostDTO.getTitle())
                .content(joinPostDTO.getContent())
                .build();
    }

    public static PostResponseDTO.JoinPostResultDTO toJoinPostResultDTO(Post post) {
        return PostResponseDTO.JoinPostResultDTO.builder()
                .postId(post.getId())
                .createAt(post.getCreatedAt())
                .build();

    }
    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post) {
        return PostResponseDTO.PostPreviewDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreatedAt())
                .updateAt(post.getUpdatedAt())
                .build();
    }
}
