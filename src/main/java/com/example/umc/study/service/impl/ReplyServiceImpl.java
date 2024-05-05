package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.ReplyHandler;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.request.ReplyRequestDTO;
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

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO) {
        Reply reply = ReplyConverter.toReply(createReplyDTO);
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
}

