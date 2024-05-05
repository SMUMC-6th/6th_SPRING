package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.apiPayload.code.BaseErrorCode;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.dto.UserResponseDTO;
import com.example.umc.study.service.PostService;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {
    private final PostService postService;

    @PostMapping("/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.PostResultDTO> createPost(@PathVariable Long userId, @RequestBody PostRequestDTO.PostDTO postDTO) {
        Post post = postService.createPost(userId, postDTO);
        return BaseResponse.onSuccess(PostConverter.toPostResultDTO(post));
    }

    @GetMapping("/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> readPost(@PathVariable Long postId) {
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    @GetMapping("/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(postList));
    }

    @DeleteMapping("/posts/{postId}")
    public BaseResponse<String> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }
    @PatchMapping("/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> updatePost(@RequestBody PostRequestDTO.UpdatePostDTO updatePostDTO,
                                                                   @PathVariable Long postId) {
        Post post = postService.updatePost(updatePostDTO, postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }
    @GetMapping("/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPostByUser(@PathVariable Long userId) {
        List<Post> posts = postService.readPostsByUser(userId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(posts));
    }
}
