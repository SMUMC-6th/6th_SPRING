package com.example.umc.study.service;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;

import java.util.List;

public interface ReplyService {
    Reply createReply(ReplyRequestDTO.JoinDTO joinDTO);

    Reply readReply(Long id);

    List<Reply> readReplies();

    void deleteReply(Long id);
}
