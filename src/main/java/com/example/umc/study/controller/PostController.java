package com.example.umc.study.controller;

import com.example.umc.study.apiPayLoad.BaseResponse;
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
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    @PostMapping("users/{userId}/posts")
    public BaseResponse<PostResponseDto.JoinResultDto> createPost(
            @PathVariable Long userId,
            @RequestBody PostRequestDto.CreatePostDto createPostDto
    ) {
        Post post = postService.createPost(userId, createPostDto);
        return BaseResponse.onSuccess(PostConverter.toJoinResultDto(post));
    }

    @GetMapping("posts/{postId}")
    public BaseResponse<PostResponseDto.PostPreviewDto> readPost(
            @PathVariable Long postId) {
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDto(post));
    }

    @GetMapping("posts")
    public BaseResponse<PostResponseDto.PostPreviewListDto> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDto(postList));
    }

    @GetMapping("users/{userId}/posts")
    public BaseResponse<PostResponseDto.PostPreviewListDto> readPostsByUser(@PathVariable Long userId) {
        List<Post> posts = postService.readPostsByUser(userId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDto(posts));
    }

    @PatchMapping("posts/{postId}")
    public BaseResponse<PostResponseDto.PostPreviewDto> updateUser(@RequestBody PostRequestDto.UpdatePostDto updatePostDto,
                                                                   @PathVariable Long postId) {
        Post post = postService.updatePost(updatePostDto, postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDto(post));
    }

    @DeleteMapping("posts/{postId}")
    public BaseResponse<String> deletePost(
            @PathVariable Long postId) {
        postService.deletePost(postId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }
}
