package com.example.umc.study.converter;

import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDto;
import com.example.umc.study.dto.ReplyResponseDto;

import java.util.List;

public class ReplyConverter {
    public static Reply toReply(ReplyRequestDto.CreateReplyDto joinDto) {
        return Reply.builder()
                .title(joinDto.getTitle())
                .content(joinDto.getContent())
                .build();
    }

    public static ReplyResponseDto.JoinResultDto toJoinResultDto(Reply reply) {
        return ReplyResponseDto.JoinResultDto.builder()
                .replyId(reply.getId())
                .title(reply.getTitle())
                .content(reply.getContent())
                .createAt(reply.getCreatedAt())
                .build();
    }

    public static ReplyResponseDto.ReplyPreviewDto toReplyPreviewDto(Reply reply) {
        return ReplyResponseDto.ReplyPreviewDto.builder()
                .replyId(reply.getId())
                .title(reply.getTitle())
                .content(reply.getContent())
                .createAt(reply.getCreatedAt())
                .updateAt(reply.getUpdatedAt())
                .build();
    }

    public static ReplyResponseDto.ReplyPreviewListDto toReplyPreviewListDto(
            List<Reply> replyList) {
        List<ReplyResponseDto.ReplyPreviewDto> replyPreviewDtoList = replyList.stream()
                .map(ReplyConverter::toReplyPreviewDto)
                .toList();
        return ReplyResponseDto.ReplyPreviewListDto.builder()
                .replyPreviewDtoList(replyPreviewDtoList)
                .build();
    }
}