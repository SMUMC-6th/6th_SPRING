package com.example.umc.study.service;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDto;

import java.util.List;

public interface ReplyService {
    Reply createReply(ReplyRequestDto.CreateReplyDto createReplyDto);

    Reply readReply(Long replyId);

    List<Reply> readReplies();

    void deleteReply(Long replyId);

    List<Reply> readRepliesByPost(Long postId);
}
