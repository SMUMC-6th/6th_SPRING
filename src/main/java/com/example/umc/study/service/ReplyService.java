package com.example.umc.study.service;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDto;

import java.util.List;

public interface ReplyService {
    Reply createReply(ReplyRequestDto.CreateReplyDto userId, Long postId, Long createReplyDto);

    Reply readReply(Long replyId);

    void deleteReply(Long replyId);

    List<Reply> readReplies();
}