package com.example.umc.study.service;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;

import java.util.List;

public interface ReplyService {
    Reply createReply(Long userId, Long postId, ReplyRequestDTO.CreateReplyDTO createReplyDTO);

    Reply readReply(Long replyId);

    List<Reply> readReplies();

    void deleteReply(Long replyId);

    List<Reply> readRepliesByPost(Long postId);

    Reply updateReply(ReplyRequestDTO.UpdateReplyDTO updateReplyDTO, Long replyId);
}
