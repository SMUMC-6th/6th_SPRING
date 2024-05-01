package com.example.umc.study.service.impl;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.ReplyHandler;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.UserRequestDTO;
import com.example.umc.study.repository.ReplyRepository;
import com.example.umc.study.repository.UserRepository;
import com.example.umc.study.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    @Override
    @Transactional
    public Reply createReply(ReplyRequestDTO.JoinReplyDTO joinReplyDTO) {
        Reply reply = ReplyConverter.toReply(joinReplyDTO);
        return replyRepository.save(reply);
    }

    @Transactional(readOnly = true)
    @Override
    public Reply readReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> {
            throw new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY);
        });
        return reply;
    }

}
