package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.ReplyHandler;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.repository.ReplyRepository;
import com.example.umc.study.service.ReplyService;
import jakarta.persistence.Temporal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional
    @Override
    public Reply createReply(ReplyRequestDTO.AddDTO addDTO) {
        Reply reply = ReplyConverter.toReply(addDTO);
        return replyRepository.save(reply);
    }

    @Override
    @Transactional(readOnly = true)
    public Reply readReply(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reply> readReplies() {
        return replyRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
        replyRepository.delete(reply);
    }
}
