package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.exception.handler.ReplyHandler;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.repository.ReplyRepository;
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
    private final PostRepository postRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO, Long userId, Long postId) {
        Reply reply = ReplyConverter.toReply(createReplyDTO);
        return replyRepository.save(reply);
    }

    @Transactional(readOnly = true)
    @Override
    public Reply readReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(()-> {
            throw new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY);
        });
        return reply;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reply> readReplies() {
        return replyRepository.findAll();
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(()-> {
            throw new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY);
        });
        replyRepository.delete(reply);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reply> readRepliesByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        return replyRepository.findAllByPost(post);
    }
}
