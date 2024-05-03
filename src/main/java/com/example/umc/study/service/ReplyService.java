package com.example.umc.study.service;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;

import java.util.List;

public interface ReplyService {

    Reply createReply(ReplyRequestDTO.AddDTO addDTO, Long userId, Long postId);

    Reply readReply(Long replyId);

    List<Reply> readReplies();

    void deleteReply(Long replyId);

    List<Reply> readReplyByPost(Long postId);
}
