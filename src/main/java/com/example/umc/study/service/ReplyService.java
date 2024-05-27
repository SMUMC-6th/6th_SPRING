package com.example.umc.study.service;


import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;

import java.util.List;

public interface ReplyService {
//    Reply createReply(ReplyRequestDTO.JoinDTO joinDTO);

    Reply readReply(Long replyId);

    List<Reply> readReplys();

    void deletePost(Long replyId);

    Reply createReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO, Long userId, Long postId);

    List<Reply> readRepliesByPost(Long postId);

    Reply updateReply(ReplyRequestDTO.UpdateReplyDTO updateReplyDTO, Long postId);
}
