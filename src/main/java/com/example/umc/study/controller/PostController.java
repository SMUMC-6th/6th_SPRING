package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.dto.response.PostResponseDTO;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public BaseResponse<PostResponseDTO.CreatePostResultDTO> createUser(@RequestBody PostRequestDTO.CreatePostDTO createPostDTO) {
        Post post = postService.createPost(createPostDTO);
        return BaseResponse.onSuccess(PostConverter.toCreatePostResultDTO(post));
    }

    @GetMapping("/posts/{postsId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> readUser(@PathVariable Long postsId) {
        Post post = postService.readPost(postsId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    @GetMapping("/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(postList));
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
