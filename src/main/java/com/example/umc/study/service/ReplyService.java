package com.example.umc.study.service;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.request.ReplyRequestDTO;
import com.example.umc.study.dto.response.ReplyResponseDTO;

import java.util.List;

public interface ReplyService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO, Long userId, Long postId);

    Reply readReply(Long replyId);

    List<Reply> readReplies();

    void deleteReply(Long replyId);

    List<Reply> findAllByPost(Long postId);

    Reply updateReply(ReplyRequestDTO.UpdateReplyDTO updateReplyDTO, Long replyId);
}
