package com.example.umc.study.service;

import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;

import java.util.List;

public interface ReplyService {
    Reply createReply(Long userId, Long postId, ReplyRequestDTO.ReplyDTO replyDTO);

    Reply readReply(Long replyId);
    List<Reply> readReply();

    void deleteReply(Long replyId);
    List<Reply> readRepliesByPost(Long postId);
}
