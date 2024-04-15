package com.example.umc.study.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.handler.PostHandler;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(PostRequestDTO.CreatePostDTO createPostDTO) {
        Post post = PostConverter.toPost(createPostDTO);
        postRepository.save(post);
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public Post readPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._POST_NOT_FOUND));
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> readPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostHandler(ErrorStatus._POST_NOT_FOUND));
        postRepository.delete(post);
    }
}
