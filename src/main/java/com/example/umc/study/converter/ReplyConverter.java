package com.example.umc.study.converter;

import com.example.umc.study.domain.Reply;

import java.util.List;

public class ReplyConverter {
    public static Reply toReply(ReplyRequestDTO.JoinDto joinDto) {
        return Reply.builder()
                .title(joinDto.getTitle())
                .content(joinDto.getContent())
                .build();
    }

    public static ReplyResponseDTO.JoinResultDto toJoinResultDto(Reply reply) {
        return ReplyResponseDTO.JoinResultDto.builder()
                .replyId(reply.getId())
                .title(reply.getTitle())
                .content(reply.getContent())
                .createAt(reply.getCreatedAt())
                .build();
    }

    public static ReplyResponseDTO.ReplyPreviewDto toReplyPreviewDto(Reply reply) {
        return ReplyResponseDTO.ReplyPreviewDto.builder()
                .replyId(reply.getId())
                .title(reply.getTitle())
                .content(reply.getContent())
                .createAt(reply.getCreatedAt())
                .updateAt(reply.getUpdatedAt())
                .build();
    }

    public static ReplyResponseDTO.ReplyPreviewListDto toReplyPreviewListDto(
            List<Reply> replyList) {
        List<ReplyResponseDTO.ReplyPreviewDto> replyPreviewDtoList = replyList.stream()
                .map(ReplyConverter::toReplyPreviewDto)
                .toList();
        return ReplyResponseDTO.ReplyPreviewListDto.builder()
                .replyPreviewDtoList(replyPreviewDtoList)
                .build();
    }
}