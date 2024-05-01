package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;

import java.util.List;

public class PostConverter {

    public static Post toPost(PostRequestDTO.UploadDTO uploadDTO) {
        return Post.builder()
                .title(uploadDTO.getTitle())
                .content(uploadDTO.getContent())
                .build();
    }

    public static PostResponseDTO.UploadResultDTO toUploadResultDTO(Post post) {
        return PostResponseDTO.UploadResultDTO.builder()
                .postId(post.getId())
                .createAt(post.getCreatedAt())
                .build();
    }

    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post) {
        return PostResponseDTO.PostPreviewDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .updateAt(post.getUpdatedAt())
                .createAt(post.getCreatedAt())
                .build();
    }

    public static PostResponseDTO.PostPreviewListDTO toPostPreviewListDTO(List<Post> postList) {
        List<PostResponseDTO.PostPreviewDTO> postPreviewDTOList = postList.stream()
                .map(PostConverter::toPostPreviewDTO)
                .toList();

        return PostResponseDTO.PostPreviewListDTO.builder()
                .postPreviewList(postPreviewDTOList)
                .build();
    }

}
