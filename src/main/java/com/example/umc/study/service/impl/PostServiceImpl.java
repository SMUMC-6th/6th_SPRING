package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.exception.handler.UserHandler;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.dto.PostRequestDto;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post createPost(Long userId, PostRequestDto.CreatePostDto createPostDto) {
        Post post = PostConverter.toPost(createPostDto);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public Post readPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> readPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Post updatePost(PostRequestDto.UpdatePostDTO updatePostDTO, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        post.updatePost(updatePostDTO.getTitle(), updatePostDTO.getContent());
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> readPostsByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        return postRepository.findAllByUser(user);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        postRepository.delete(post);
    }
}