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
    private final PostService postService; // DI

    @PostMapping("/api/v1/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.CreatePostResultDTO> createPost(@PathVariable Long userId, @RequestBody PostRequestDTO.CreatePostDTO createPostDTO) {
        Post post = postService.createPost(userId, createPostDTO);
        return BaseResponse.onSuccess(PostConverter.toCreatePostResultDTO(post));
    }

    @GetMapping("/api/v1/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> readPost(@PathVariable Long postId){
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    @GetMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts() {
        List<Post> posts = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(posts));
    }

    @DeleteMapping("/api/v1/posts/{postId}")
    public BaseResponse<String> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return BaseResponse.onSuccess("삭제되었습니다.");
    }


}
