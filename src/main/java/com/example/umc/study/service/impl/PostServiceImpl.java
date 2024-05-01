package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.exception.handler.UserHandler;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post createPost(PostRequestDTO.JoinPostDTO joinPostDTO) {
        Post post = PostConverter.toPost(joinPostDTO);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    @Override
    public Post readPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            throw new PostHandler(ErrorStatus._NOT_FOUND_POST);
        });
        return post;
    }

}
