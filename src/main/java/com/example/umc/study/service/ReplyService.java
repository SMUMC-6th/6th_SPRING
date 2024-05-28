package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.ReplyRequestDTO;

import java.util.List;

public interface ReplyService {
    Reply createReply(Long userId, Long postId, ReplyRequestDTO.JoinReplyDTO joinReplyDTO);

    Reply readReply(Long replyId);

    void deleteReply(Long replyId);

    List<Reply> readReplies();

    List<Reply> readRepliesByPost(Long postId);

    Reply updateReply(ReplyRequestDTO.UpdateReplyDTO updateReplyDTO, Long replyId);
}
