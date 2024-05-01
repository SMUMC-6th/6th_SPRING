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
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    //게시물 작성하기
    @PostMapping("/{userId}")
    public BaseResponse<PostResponseDTO.CreatePostResultDTO> createPost(@PathVariable Long userId, @RequestBody PostRequestDTO.CreatePostDTO createPostDTO){
        Post post = postService.createPost(userId,createPostDTO);
        return BaseResponse.onSuccess(PostConverter.toCreatePostResultDTO(post));
    }

    // postId로 게시물 불러오기
    @GetMapping("/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> readPost(@PathVariable Long postId){
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    // userId로 게시물 불러오기
    @GetMapping("/users/{userId}")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPostByUserId(@PathVariable Long userId){
        List<Post> postList = postService.readPostsByUserId(userId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(postList));
    }

    // 게시물 전체 불러오기
    @GetMapping("/list")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts(){
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(postList));
    }

    // 게시물 삭제하기
    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId){postService.deletePost(postId);}
}
