package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.UserHandler;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Post createPost(Long userId, PostRequestDTO.CreatePostDTO createPostDTO) {
        Post post = PostConverter.toPost(createPostDTO);
        User user = userRepository.findById(userId).orElseThrow(()->{
            throw new UserHandler(ErrorStatus. _NOT_FOUND_USER);
        });
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    @Transactional(readOnly = true)
    @Override
    public Post readPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(()-> new PostHandler(ErrorStatus._NOT_FOUND_POST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> readPosts() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostHandler(ErrorStatus._NOT_FOUND_POST)); // Post니까 postHandler로
        postRepository.delete(post);
    }

}
