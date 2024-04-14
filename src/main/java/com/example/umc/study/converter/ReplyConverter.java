package com.example.umc.study.converter;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;

import java.util.List;

public class ReplyConverter {

    public static Reply toReply(ReplyRequestDTO.JoinDTO joinDTO) {
        return Reply.builder()
                .title(joinDTO.getTitle())
                .content(joinDTO.getContent())
                .build();
    }

    public static ReplyResponseDTO.JoinResultDTO toJoinResultDTO(Reply reply) {
        return ReplyResponseDTO.JoinResultDTO.builder()
                .replyId(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyResponseDTO.ReplyPreviewDTO.builder()
                .replyId(reply.getId())
                .title(reply.getTitle())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }

    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replyList) {
        return ReplyResponseDTO.ReplyPreviewListDTO.builder()
                .replyPreviewDTOList(replyList.stream()
                        .map(ReplyConverter::toReplyPreviewDTO)
                        .toList()
                ).build();
    }
}
