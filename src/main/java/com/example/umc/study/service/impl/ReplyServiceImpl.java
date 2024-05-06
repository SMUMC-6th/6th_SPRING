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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public Reply createReply(Long userId, Long postId, ReplyRequestDTO.ReplyDTO replyDTO) {
        Reply reply = ReplyConverter.toReply(replyDTO);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        reply.setUser(user);
        reply.setPost(post);
        replyRepository.save(reply);
        return reply;
    }

    @Transactional(readOnly = true)
    @Override
    public Reply readReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));

        return reply;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reply> readReply() { return replyRepository.findAll(); }

    @Transactional
    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
        replyRepository.delete(reply);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Reply> readRepliesByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_POST));
        return replyRepository.findAllByPost(post);
    }
}
