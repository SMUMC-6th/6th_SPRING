package com.example.umc.study.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.request.ReplyRequestDTO;
import com.example.umc.study.handler.ReplyHandler;
import com.example.umc.study.repository.ReplyRepository;
import com.example.umc.study.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO) {
        Reply reply = ReplyConverter.toReply(createReplyDTO);
        replyRepository.save(reply);
        return reply;
    }

    @Override
    @Transactional(readOnly = true)
    public Reply readReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new ReplyHandler(ErrorStatus._REPLY_NOT_FOUND));
        return reply;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reply> readReplies() {
        return replyRepository.findAll();
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new ReplyHandler(ErrorStatus._REPLY_NOT_FOUND));
        replyRepository.delete(reply);
    }
}
