package com.example.umc.study.converter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.request.ReplyRequestDTO;
import com.example.umc.study.dto.response.ReplyResponseDTO;

import java.util.List;

public class ReplyConverter {

    public static Reply toReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO){
        return Reply.builder()
                .content(createReplyDTO.getContent())
                .build();
    }

    public static ReplyResponseDTO.CreateReplyResultDTO toCreateReplyResultDTO(Reply reply){
        return ReplyResponseDTO.CreateReplyResultDTO.builder()
                .replyId(reply.getId())
                .userId(reply.getId())
                .postId(reply.getId())
                .build();
    }

    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply){
        return ReplyResponseDTO.ReplyPreviewDTO.builder()
                .replyId(reply.getId())
                .content(reply.getContent())
                .userId(reply.getId())
                .postId(reply.getId())
                .createAt(reply.getCreatedAt())
                .updateAt(reply.getUpdatedAt())
                .build();
    }
    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replyList){
        List<ReplyResponseDTO.ReplyPreviewDTO> replyPreviewDTOList = replyList.stream()
                .map(ReplyConverter::toReplyPreviewDTO)
                .toList();

        return ReplyResponseDTO.ReplyPreviewListDTO.builder()
                .replyPreviewDTOList(replyPreviewDTOList)
                .build();
    }
}
