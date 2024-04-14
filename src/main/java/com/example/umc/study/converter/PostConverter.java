package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;

import java.util.List;

public class PostConverter {

    public static Post toPost(PostRequestDTO.JoinDTO joinDTO) {
        return Post.builder()
                .title(joinDTO.getTitle())
                .content(joinDTO.getContent())
                .build();
    }

    public static PostResponseDTO.JoinResultDTO toJoinResultDTO(Post post) {
        return PostResponseDTO.JoinResultDTO.builder()
                .postId(post.getId())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post) {
        return PostResponseDTO.PostPreviewDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostResponseDTO.PostPreviewListDTO toPostPreviewListDTO(List<Post> postList) {
        return PostResponseDTO.PostPreviewListDTO.builder()
                .postPreviewDTOList(
                        postList.stream()
                                .map(PostConverter::toPostPreviewDTO)
                                .toList()
                ).build();
    }
}
