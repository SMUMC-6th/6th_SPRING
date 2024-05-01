package com.example.umc.study.converter;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;

public class ReplyConverter {

    public static Reply toReply(ReplyRequestDTO.JoinReplyDTO joinReplyDTO) {
        return Reply.builder()
                .title(joinReplyDTO.getTitle())
                .content(joinReplyDTO.getContent())
                .build();
    }

    public static ReplyResponseDTO.JoinReplyResultDTO toJoinReplyResultDTO(Reply reply) {
        return ReplyResponseDTO.JoinReplyResultDTO.builder()
                .replyId(reply.getId())
                .createAt(reply.getCreatedAt())
                .build();

    }

    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyResponseDTO.ReplyPreviewDTO.builder()
                .replyId(reply.getId())
                .title(reply.getTitle())
                .content(reply.getContent())
                .updateAt(reply.getUpdatedAt())
                .createAt(reply.getCreatedAt())
                .build();
    }
}
