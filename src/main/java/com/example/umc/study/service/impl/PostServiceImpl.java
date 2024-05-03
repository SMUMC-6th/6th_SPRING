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

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(PostRequestDTO.UploadDTO uploadDTO) {
        Post post = PostConverter.toPost(uploadDTO);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    @Override
    //readOnly는 데이터베이스를 건들지 않는 것으로 직접적으로 데이터를 변화시켜야하는 create, update, delete에는 쓰면 안되는 옵션
    public Post readPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> readPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->new PostHandler(ErrorStatus._NOT_FOUND_POST));
        postRepository.delete(post);
    }
}
