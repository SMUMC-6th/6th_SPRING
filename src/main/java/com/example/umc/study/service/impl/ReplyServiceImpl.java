package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.exception.handler.ReplyHandler;
import com.example.umc.study.apiPayload.exception.handler.UserHandler;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.repository.ReplyRepository;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Reply createReply(Long userId, Long postId, ReplyRequestDTO.CreateReplyDTO createReplyDTO) {
        Reply reply = ReplyConverter.toReply(createReplyDTO);
        // reply와 user 및 post 연관관계
        User user = userRepository.findById(userId).orElseThrow(()->{
            throw new UserHandler(ErrorStatus._NOT_FOUND_USER);});
        Post post = postRepository.findById(postId).orElseThrow(()->{
            throw new PostHandler(ErrorStatus. _NOT_FOUND_POST);});
        reply.setUser(user);
        reply.setPost(post);
        replyRepository.save(reply);
        return reply;
    }

    @Transactional(readOnly = true)
    @Override
    public Reply readReply(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(()-> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reply> readReplies() {
        return replyRepository.findAll();
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(()-> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
        replyRepository.delete(reply);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reply> readRepliesByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->{
            throw new PostHandler(ErrorStatus. _NOT_FOUND_POST);});
        return replyRepository.findAllByPost(post);
    }

}
