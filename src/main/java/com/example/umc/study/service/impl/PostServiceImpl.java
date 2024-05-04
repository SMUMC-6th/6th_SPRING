package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.exception.handler.UserHandler;
import com.example.umc.study.converter.PostConverter;
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

@RequiredArgsConstructor
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post createPost(Long userId, PostRequestDTO.JoinDTO joinDTO) {

        Post post = PostConverter.toPost(joinDTO);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        post.setUser(user);
        return postRepository.save(post);
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
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        postRepository.delete(post);
    }

    @Override
    public Post updatePost(Long id, PostRequestDTO.UpdatePostDTO updatePostDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        post.update(updatePostDTO.getTitle(), updatePostDTO.getContent());
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> readPostsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        return postRepository.findAllByUser(user);
    }
}
