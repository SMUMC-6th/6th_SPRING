package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.dto.UserResponseDTO;
import com.example.umc.study.service.PostService;
import com.example.umc.study.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.JoinPostResultDTO> createPost(@RequestBody PostRequestDTO.JoinPostDTO joinPostDTO) {
        Post post = postService.createPost(joinPostDTO);
        return BaseResponse.onSuccess(PostConverter.toJoinPostResultDTO(post));
    }

    @GetMapping("/api/v1/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> readPost(@PathVariable Long postId) {
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

/*
    @DeleteMapping("/api/v1/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
*/

    @DeleteMapping("/api/v1/posts/{postId}")
    public BaseResponse<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }




    @GetMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toJoinPostPreviewListDTO(postList));
    }

}
