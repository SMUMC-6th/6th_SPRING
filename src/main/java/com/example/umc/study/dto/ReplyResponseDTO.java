package com.example.umc.study.dto;

import com.example.umc.study.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResponseDTO {

    //리턴하고 싶은 데이터
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AddResultDTO {

        private Long replyId;
        private LocalDateTime createAt;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReplyPreviewDTO {
        private Long replyId;
        private String title;
        private String content;
        private LocalDateTime updateAt;
        private LocalDateTime createAt;

    }

    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyPreviewDTO.builder()
                .replyId(reply.getId())
                .title(reply.getTitle())
                .content(reply.getContent())
                .updateAt(reply.getUpdatedAt())
                .createAt(reply.getCreatedAt())
                .build();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReplyPreviewListDTO {
        List<ReplyPreviewDTO> replyPreviewDTOList;
    }
}
