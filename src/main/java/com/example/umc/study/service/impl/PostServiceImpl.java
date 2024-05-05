package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.exception.handler.UserHandler;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.request.PostRequestDTO;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.PostService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post createPost(Long userId, PostRequestDTO.CreatePostDTO createPostDTO) {
        Post post = PostConverter.toPost(createPostDTO);
        User user = userRepository.findById(userId).orElseThrow(()-> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        post.setUser(user);
        postRepository.save(post);
        return post;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Post> readPosts(){
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Post readPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()->{
            throw new PostHandler((ErrorStatus._NOT_FOUND_POST));
        });
        return post;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> readPostsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        return postRepository.findAllByUser(user);
    }
    //post 수정
    @Override
    public Post updatePost(PostRequestDTO.UpdatePostDTO updatePostDTO,Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        post.update(updatePostDTO.getTitle(), updatePostDTO.getContent());
        return post;
    }

    @Override
    public void deletePost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        postRepository.delete(post);
    }
}
