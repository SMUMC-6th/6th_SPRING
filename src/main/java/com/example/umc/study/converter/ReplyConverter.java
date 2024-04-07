package com.example.umc.study.converter;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.request.ReplyRequestDTO;
import com.example.umc.study.dto.response.ReplyResponseDTO;

import java.util.List;

public class ReplyConverter {

    public static Reply toReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO) {
        return Reply.builder()
                .title(createReplyDTO.getTitle())
                .content(createReplyDTO.getContent())
                .build();
    }

    public static ReplyResponseDTO.CreateReplyResultDTO toCreateReplyResultDTO(Reply reply) {
        return ReplyResponseDTO.CreateReplyResultDTO.builder()
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

    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replies) {
        List<ReplyResponseDTO.ReplyPreviewDTO> replyPreviewDTOList = replies.stream().map(ReplyConverter::toReplyPreviewDTO).toList();

        return ReplyResponseDTO.ReplyPreviewListDTO
                .builder()
                .replyPreviewDTOList(replyPreviewDTOList)
                .build();
    }
}
