package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDto;
import com.example.umc.study.dto.ReplyResponseDto;
import com.example.umc.study.service.ReplyService;
import com.example.umc.study.converter.ReplyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/replies")
    public BaseResponse<ReplyResponseDto.JoinResultDto> createPost(
            @RequestBody ReplyRequestDto.JoinDto joinDto
    ) {
        Reply reply = replyService.createReply(joinDto);
        return BaseResponse.onSuccess(ReplyConverter.toJoinResultDto(reply));
    }

    @GetMapping("/replies/{replyId}")
    public BaseResponse<ReplyResponseDto.ReplyPreviewDto> readPost(
            @PathVariable Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDto(reply));
    }

    @GetMapping("/replies")
    public BaseResponse<ReplyResponseDto.ReplyPreviewListDto> readPosts() {
        List<Reply> postList = replyService.readReplies();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDto(postList));
    }

    @DeleteMapping("/replies/{replyId}")
    public BaseResponse<String> deletePost(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }
}