package com.example.umc.study.service;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.ReplyHandler;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.JoinDTO joinDTO) {
        Reply reply = ReplyConverter.toReply(joinDTO);
        return replyRepository.save(reply);
    }

    @Override
    public Reply readReply(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(()->new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
    }

    @Override
    public List<Reply> readReplys() {
        return replyRepository.findAll();
    }

    @Override
    public void deletePost(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(()-> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
        replyRepository.delete(reply);
    }
}
