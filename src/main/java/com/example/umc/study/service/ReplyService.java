package com.example.umc.study.service;

import com.example.umc.study.domain.Reply;

import java.util.List;

public interface ReplyService {
    Reply createReply(ReplyRequestDTO.JoinDto joinDto);

    Reply readReply(Long replyId);

    List<Reply> readReplies();

    void deleteReply(Long replyId);
}
