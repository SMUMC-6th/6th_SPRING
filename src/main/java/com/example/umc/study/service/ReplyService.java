package com.example.umc.study.service;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.ReplyRequestDTO;

public interface ReplyService {
    Reply createReply(ReplyRequestDTO.JoinReplyDTO joinReplyDTO);

    Reply readReply(Long replyId);
}
