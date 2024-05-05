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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.PostResultDTO> createPost(@RequestBody PostRequestDTO.PostDTO postDTO) {
        Post post = postService.createPost(postDTO);
        return BaseResponse.onSuccess(PostConverter.toPostResultDTO(post));
    }

    @GetMapping("/api/v1/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> readPost(@PathVariable Long postId) {
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    @GetMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(postList));
    }

    @DeleteMapping("/api/v1/posts/{postId}")
    public BaseResponse<String> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }
}
