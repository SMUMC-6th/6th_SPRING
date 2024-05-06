package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.exception.handler.ReplyHandler;
import com.example.umc.study.apiPayload.exception.handler.UserHandler;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.ReplyRequestDto;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.repository.ReplyRepository;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.ReplyService;
import com.example.umc.study.converter.ReplyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Reply createReply(ReplyRequestDto.CreateReplyDto createReplyDto, Long userId, Long postId) {
        Reply reply = ReplyConverter.toReply(createReplyDto);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        reply.setUser(user);
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        reply.setPost(post);
        replyRepository.save(reply);
        return replyRepository.save(reply);
    }

    @Override
    @Transactional(readOnly = true)
    public Reply readReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
        return reply;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reply> readReplies() {
        List<Reply> replies = replyRepository.findAll();
        return replies;
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
        replyRepository.delete(reply);
    }
}