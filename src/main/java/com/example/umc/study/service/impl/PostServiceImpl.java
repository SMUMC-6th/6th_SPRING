package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post createPost(PostRequestDTO.JoinDTO joinDTO) {
        return postRepository.save(PostConverter.toPost(joinDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Post readPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> readPosts() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        postRepository.delete(post);
    }
}
