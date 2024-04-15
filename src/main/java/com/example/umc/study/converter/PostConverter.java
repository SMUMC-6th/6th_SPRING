package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;

import java.util.List;

public class PostConverter {
    public static Post toPost(PostRequestDTO.JoinDto joinDto) {
        return Post.builder()
                .title(joinDto.getTitle())
                .content(joinDto.getContent())
                .build();
    }

    public static PostResponseDTO.JoinResultDto toJoinResultDto(Post post) {
        return PostResponseDTO.JoinResultDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreatedAt())
                .build();
    }

    public static PostResponseDTO.PostPreviewDto toPostPreviewDto(Post post) {
        return PostResponseDTO.PostPreviewDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreatedAt())
                .updateAt(post.getUpdatedAt())
                .build();
    }

    public static PostResponseDTO.PostPreviewListDto toPostPreviewListDto(List<Post> postList) {
        List<PostResponseDTO.PostPreviewDto> postPreviewDtoList = postList.stream()
                .map(PostConverter::toPostPreviewDto)
                .toList();
        return PostResponseDTO.PostPreviewListDto.builder()
                .postPreviewDtoList(postPreviewDtoList)
                .build();
    }
}