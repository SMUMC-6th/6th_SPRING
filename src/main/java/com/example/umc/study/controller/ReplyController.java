package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;
import com.example.umc.study.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/api/v1/replies")
    public BaseResponse<ReplyResponseDTO.JoinResultDto> createPost(@RequestBody ReplyRequestDTO.JoinDto joinDto
    ) {
        Reply reply = replyService.createReply(joinDto);
        return BaseResponse.onSuccess(ReplyConverter.toJoinResultDto(reply));
    }

    @GetMapping("/api/v1/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDto> readPost(
            @PathVariable Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDto(reply));
    }

    @GetMapping("/api/v1/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDto> readPosts() {
        List<Reply> postList = replyService.readReplies();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDto(postList));
    }

    @DeleteMapping("/api/v1/replies/{replyId}")
    public void deletePost(
            @PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }
}
