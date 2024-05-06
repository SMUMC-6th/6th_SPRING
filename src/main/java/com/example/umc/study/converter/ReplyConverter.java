package com.example.umc.study.converter;

import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;

import java.util.List;

public class ReplyConverter {
    public static Reply toReply(ReplyRequestDTO.ReplyDTO replyDTO) {
        return Reply.builder()
                .title(replyDTO.getTitle())
                .content(replyDTO.getContent())
                .build();
    }

    public static ReplyResponseDTO.ReplyResultDTO toReplyResultDTO(Reply reply) {
        return ReplyResponseDTO.ReplyResultDTO.builder()
                .replyId(reply.getId())
                .createAt(reply.getCreatedAt())
                .build();
    }

    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyResponseDTO.ReplyPreviewDTO.builder()
                .replyId(reply.getId())
                .title(reply.getTitle())
                .content(reply.getContent())
                .createAt(reply.getCreatedAt())
                .updateAt(reply.getUpdatedAt())
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
