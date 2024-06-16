package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayLoad.code.status.ErrorStatus;
import com.example.umc.study.apiPayLoad.exception.handler.PostHandler;
import com.example.umc.study.apiPayLoad.exception.handler.ReplyHandler;
import com.example.umc.study.apiPayLoad.exception.handler.UserHandler;
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

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Reply createReply(ReplyRequestDto.CreateReplyDto createReplyDto) {
        Reply reply = ReplyConverter.toReply(createReplyDto);
        replyRepository.save(reply);
        return replyRepository.save(reply);
    }

    @Override
    @Transactional(readOnly = true)
    public Reply readReply(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reply> readReplies() {
        return replyRepository.findAll();
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
        replyRepository.delete(reply);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reply> readRepliesByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        return replyRepository.findAllByPost(post);
    }
}
