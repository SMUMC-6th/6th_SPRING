package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.dto.response.PostResponseDTO;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    @PostMapping("/create/post/{userId}")
    public BaseResponse<PostResponseDTO.CreatePostResultDTO> createPost(@PathVariable Long userId, @RequestBody PostRequestDTO.CreatePostDTO createPostDTO){
        Post post = postService.createPost(userId,createPostDTO);
        return BaseResponse.onSuccess(PostConverter.toCreatePostResultDTO(post));
    }
}
