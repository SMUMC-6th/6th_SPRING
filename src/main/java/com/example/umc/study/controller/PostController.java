package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDto;
import com.example.umc.study.dto.PostResponseDto;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public BaseResponse<PostResponseDto.JoinResultDto> createPost(
            @RequestBody PostRequestDto.JoinDto joinDto
    ) {
        Post post = postService.createPost(joinDto);
        return BaseResponse.onSuccess(PostConverter.toJoinResultDto(post));
    }

    @GetMapping("/api/v1/posts/{postId}")
    public BaseResponse<PostResponseDto.PostPreviewDto> readPost(
            @PathVariable Long postId) {
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDto(post));
    }

    @GetMapping("/api/v1/posts")
    public BaseResponse<PostResponseDto.PostPreviewListDto> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDto(postList));
    }

    @DeleteMapping("/posts/{postId}")
    public BaseResponse<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }
}