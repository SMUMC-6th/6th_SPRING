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
<<<<<<< Updated upstream
=======
@RequestMapping("/api/v1")
@CrossOrigin("*")
>>>>>>> Stashed changes
public class PostController {

    private final PostService postService;

<<<<<<< Updated upstream
    @PostMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.JoinResultDTO> createPost(@RequestBody PostRequestDTO.JoinDTO joinDTO) {
        Post post = postService.createPost(joinDTO);
        return BaseResponse.onSuccess(PostConverter.toJoinResultDTO(post));

    }
    @GetMapping("/api/v1/posts/{postId}")
=======
//    @PostMapping("/posts")
//    public BaseResponse<PostResponseDTO.JoinResultDTO> createPost(@RequestBody PostRequestDTO.CreatePostDTO createPostDTO) {
//        Post post = postService.createPost(createPostDTO);
//        return BaseResponse.onSuccess(PostConverter.toJoinResultDTO(post));
//
//    }

    @GetMapping("/posts/{postId}")
>>>>>>> Stashed changes
    public BaseResponse<PostResponseDTO.PostPreviewDTO> readPost(@PathVariable("postId") Long postId) {
        Post post = postService.readPost(postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    @GetMapping("/api/v1/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPosts() {
        List<Post> postList = postService.readPosts();
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(postList));
    }


<<<<<<< Updated upstream

    @DeleteMapping("/api/v1/posts/{postId}")
    public void deleteUser(@PathVariable("postId") Long postId) {
=======
    @DeleteMapping("/posts/{postId}")
    public BaseResponse<String> deleteUser(@PathVariable("postId") Long postId) {
>>>>>>> Stashed changes
        postService.deletePost(postId);
    }

    @PatchMapping("/posts/{postId}")
    public BaseResponse<PostResponseDTO.PostPreviewDTO> updatePost(@RequestBody PostRequestDTO.UpdatePostDTO updatePostDTO,
                                                                   @PathVariable("postId") Long postId) {
        Post post = postService.updatePost(updatePostDTO, postId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewDTO(post));
    }

    @GetMapping("/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.PostPreviewListDTO> readPostByUser(@PathVariable("userId") Long userId) {
        List<Post> posts = postService.readPostsByUser(userId);
        return BaseResponse.onSuccess(PostConverter.toPostPreviewListDTO(posts));
    }

    @PostMapping("/users/{userId}/posts")
    public BaseResponse<PostResponseDTO.CreatePostResultDTO> createPost(@PathVariable("userId") Long userId, @RequestBody PostRequestDTO.CreatePostDTO createPostDTO) {
        Post post = postService.createPost(createPostDTO, userId);
        return BaseResponse.onSuccess(PostConverter.torCreatePostResultDTO(post));
    }


}
