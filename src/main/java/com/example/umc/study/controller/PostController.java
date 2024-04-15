package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.JoinResultDto> createPost(
            @RequestBody PostRequestDTO.JoinDto joinDto
    ) {
        Post post = postService.createPost(joinDto);
        return BaseResponse.onSuccess(PostConverter.toJoinResultDto(post));
    }

    @GetMapping("/api/v1/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDto> readPost(
            @PathVariable Long postId) {
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDto(post));
    }

    @GetMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDto> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDto(postList));
    }

    @DeleteMapping("/api/v1/posts/{postId}")
    public void deletePost(
            @PathVariable Long postId) {
        postService.deletePost(postId);
    }


}
