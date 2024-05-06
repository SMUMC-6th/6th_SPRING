package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.dto.response.PostResponseDTO;

import java.util.List;

public class PostConverter {

    public static Post toPost(PostRequestDTO.CreatePostDTO createPostDTO) {
        return Post.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .build();
    }

    public static PostResponseDTO.CreatePostResultDTO toCreatePostResultDTO(Post post) {
        return PostResponseDTO.CreatePostResultDTO.builder()
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

    public static PostResponseDTO.PostPreviewListDTO toPostPreviewListDTO(List<Post> posts) {

        List<PostResponseDTO.PostPreviewDTO> postPreviewDTOList = posts.stream().map(PostConverter::toPostPreviewDTO).toList();

        return PostResponseDTO.PostPreviewListDTO.builder()
                .postPreviewDTOList(postPreviewDTOList)
                .build();
    }

}
