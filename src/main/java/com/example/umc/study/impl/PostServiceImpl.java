package com.example.umc.study.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.handler.PostHandler;
import com.example.umc.study.handler.UserHandler;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public Post createPost(PostRequestDTO.CreatePostDTO createPostDTO, Long userId) {
        Post post = PostConverter.toPost(createPostDTO);
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new UserHandler(ErrorStatus._USER_NOT_FOUND));
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public Post readPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._POST_NOT_FOUND));
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

    @Override
    public Post updatePost(PostRequestDTO.UpdatePostDTO updatePostDTO, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostHandler(ErrorStatus._POST_NOT_FOUND));
        post.update(updatePostDTO.getTitle(), updatePostDTO.getContent());
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> readPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus._USER_NOT_FOUND));
        return postRepository.findAllByUser(user);
    }
}
