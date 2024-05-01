package com.example.umc.study.converter;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;

import java.util.List;

public class ReplyConverter {

    public static Reply toReply(ReplyRequestDTO.AddDTO addDTO) {
        return Reply.builder()
                .title(addDTO.getTitle())
                .content(addDTO.getContent())
                .build();
    }

    public static ReplyResponseDTO.AddResultDTO toAddResultDTO(Reply reply) {
        return ReplyResponseDTO.AddResultDTO.builder()
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

    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replyList) {
        List<ReplyResponseDTO.ReplyPreviewDTO> replyPreviewDTOList = replyList.stream()
                .map(ReplyConverter::toReplyPreviewDTO)
                .toList();

        return ReplyResponseDTO.ReplyPreviewListDTO.builder()
                .replyPreviewDTOList(replyPreviewDTOList)
                .build();
    }
}
