package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;
import com.example.umc.study.service.ReplyService;
import com.example.umc.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    @PostMapping("/api/v1/replies")
    public BaseResponse<ReplyResponseDTO.JoinReplyResultDTO> createReply(@RequestBody ReplyRequestDTO.JoinReplyDTO joinReplyDTO) {
        Reply reply = replyService.createReply(joinReplyDTO);
        return BaseResponse.onSuccess(ReplyConverter.toJoinReplyResultDTO(reply));
    }

    @GetMapping("/api/v1/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReply(@PathVariable Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }
}
