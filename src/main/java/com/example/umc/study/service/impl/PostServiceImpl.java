package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.dto.PostRequestDto;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post createPost(PostRequestDto.JoinDto joinDto) {
        Post post = PostConverter.toPost(joinDto);
        return postRepository.save(post);
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
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        postRepository.delete(post);
    }
}