package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.dto.response.PostResponseDTO;
import com.example.umc.study.service.PostService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;
    
    @PostMapping("/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.CreatePostResultDTO> createPost(@PathVariable Long userId, @RequestBody PostRequestDTO.CreatePostDTO createPostDTO) {
        Post post = postService.createPost(userId, createPostDTO);
        return BaseResponse.onSuccess(PostConverter.toCreatePostResultDTO(post));
    }

    @GetMapping("/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> readPost(@PathVariable Long postId) {
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    @GetMapping("/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts() {
        List<Post> posts = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(posts));
    }

    @DeleteMapping("/posts/{postId}")
    public BaseResponse<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return BaseResponse.onSuccess("삭제에 성공하였습니다.");
    }

    @GetMapping("/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPostsByUser(@PathVariable Long userId) {
        List<Post> posts = postService.readPostsByUser(userId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(posts));
    }

    @PatchMapping("/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> updatePost(@RequestBody PostRequestDTO.UpdatePostDTO updatePostDTO, @PathVariable Long postId) {
        Post post = postService.updatePost(updatePostDTO,postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }
}
