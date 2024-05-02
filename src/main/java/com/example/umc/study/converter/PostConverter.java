package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDto;
import com.example.umc.study.dto.PostResponseDto;

import java.util.List;

public class PostConverter {
    public static Post toPost(PostRequestDto.JoinDto joinDto) {
        return Post.builder()
                .title(joinDto.getTitle())
                .content(joinDto.getContent())
                .build();
    }

    public static PostResponseDto.JoinResultDto toJoinResultDto(Post post) {
        return PostResponseDto.JoinResultDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreatedAt())
                .build();
    }

    public static PostResponseDto.PostPreviewDto toPostPreviewDto(Post post) {
        return PostResponseDto.PostPreviewDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreatedAt())
                .updateAt(post.getUpdatedAt())
                .build();
    }

    public static PostResponseDto.PostPreviewListDto toPostPreviewListDto(List<Post> postList) {
        List<PostResponseDto.PostPreviewDto> postPreviewDtoList = postList.stream()
                .map(PostConverter::toPostPreviewDto)
                .toList();
        return PostResponseDto.PostPreviewListDto.builder()
                .postPreviewDtoList(postPreviewDtoList)
                .build();
    }
}