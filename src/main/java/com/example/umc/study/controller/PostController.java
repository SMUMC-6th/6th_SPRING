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
@RequestMapping("/api/v1")
@CrossOrigin
public class PostController {
    private final PostService postService;
    @PostMapping("/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.JoinPostResultDTO> createPost(@PathVariable Long userId, @RequestBody PostRequestDTO.JoinPostDTO joinPostDTO) {
        Post post = postService.createPost(userId, joinPostDTO);
        return BaseResponse.onSuccess(PostConverter.toJoinPostResultDTO(post));
    }

    @GetMapping("/posts/{postId}")
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

    @DeleteMapping("/posts/{postId}")
    public BaseResponse<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }


    @GetMapping("/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toJoinPostPreviewListDTO(postList));
    }

    @PatchMapping("/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> updatePost(@RequestBody PostRequestDTO.UpdatePostDTO updatePostDTO,
                                                                   @PathVariable Long postId) {
        Post post = postService.updatePost(updatePostDTO, postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    //userId로 모든 post 찾기
    @GetMapping("/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPostsByUser(@PathVariable Long userId) {
        List<Post> posts = postService.readPostsByUser(userId);
        return BaseResponse.onSuccess(PostConverter.toJoinPostPreviewListDTO(posts));
    }

}
