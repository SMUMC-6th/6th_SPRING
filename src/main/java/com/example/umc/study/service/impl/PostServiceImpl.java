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

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post createPost(PostRequestDTO.CreatePostDTO createPostDTO, Long userId) {

        Post post = PostConverter.toPost(createPostDTO);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    @Override
    public Post readPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> {
            throw new PostHandler(ErrorStatus._NOT_FOUND_POST);
        });
        return post;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> readPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> {
            throw new PostHandler(ErrorStatus._NOT_FOUND_POST);
        });
        postRepository.delete(post);
    }
    @Override
    public Post updatePost(PostRequestDTO.UpdatePostDTO updatePostDTO, Long postId) {
        Post post = readPost(postId);
        post.update(updatePostDTO.getTitle(), updatePostDTO.getContent());
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> readPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_POST));
        return postRepository.findAllByUser(user);
    }
}
