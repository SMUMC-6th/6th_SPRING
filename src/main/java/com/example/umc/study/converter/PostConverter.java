package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;

import java.util.List;

public class PostConverter {

    public static Post toPost(PostRequestDTO.CreatePostDTO createPostDTO) {
        return Post.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .build();
    }

    public static PostResponseDTO.JoinResultDTO toJoinResultDTO(Post post) {
        return PostResponseDTO.JoinResultDTO.builder()
                .postId(post.getId()).build();
    }

    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post) {
        return PostResponseDTO.PostPreviewDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .updateAt(post.getUpdatedAt())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static PostResponseDTO.PostPreviewListDTO toPostPreviewListDTO(List<Post> postList) {
        List<PostResponseDTO.PostPreviewDTO> postPreviewDTOList = postList.stream()
                .map(PostConverter::toPostPreviewDTO)
                .toList();

        return PostResponseDTO.PostPreviewListDTO.builder()
                .postPreviewDTOList(postPreviewDTOList)
                .build();
    }

    public static PostResponseDTO.CreatePostResultDTO torCreatePostResultDTO(Post post) {
        return  PostResponseDTO.CreatePostResultDTO.builder()
                .postId(post.getId())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
