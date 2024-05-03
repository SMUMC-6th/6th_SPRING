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


    //게시글 작성
    @Override
    public Post createPost(PostRequestDTO.UploadDTO uploadDTO, Long userId) {
        Post post = PostConverter.toPost(uploadDTO);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    //게시글 조회
    @Transactional(readOnly = true)
    @Override
    //readOnly는 데이터베이스를 건들지 않는 것으로 직접적으로 데이터를 변화시켜야하는 create, update, delete에는 쓰면 안되는 옵션
    public Post readPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
    }

    //게시글 전체 조회
    @Transactional(readOnly = true)
    @Override
    public List<Post> readPosts() {
        return postRepository.findAll();
    }

    //게시글 삭제
    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->new PostHandler(ErrorStatus._NOT_FOUND_POST));
        postRepository.delete(post);
    }

    //게시글 업데이트
    @Override
    public Post updatePost(PostRequestDTO.updatePostDTO updatePostDTO, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        post.update(updatePostDTO.getTitle(), updatePostDTO.getContent());
        return post;
    }

    //user로 모든 게시글 찾기
    @Override
    @Transactional(readOnly = true)
    public List<Post> readPostByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->new UserHandler(ErrorStatus._NOT_FOUND_USER));
        return postRepository.findAllByUser(user);
    }
}
