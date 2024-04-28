package com.example.umc.study.service;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(PostRequestDTO.JoinDTO joinDTO) {
        Post post = PostConverter.toPost(joinDTO);
        return postRepository.save(post);
    }

    @Override
    public Post readPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(()-> new PostHandler(ErrorStatus._NOT_FOUND_POST));
    }

    @Override
    public List<Post> readPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        postRepository.delete(post);
    }
}
