package com.example.umc.study.controller;

import com.example.umc.study.apiPayLoad.BaseResponse;
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
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/replies")
    public BaseResponse<ReplyResponseDto.JoinResultDto> createReply(
            @RequestBody ReplyRequestDto.CreateReplyDto createReplyDto
    ) {
        Reply reply = replyService.createReply(createReplyDto);
        return BaseResponse.onSuccess(ReplyConverter.toJoinResultDto(reply));
    }

    @GetMapping("replies/{replyId}")
    public BaseResponse<ReplyResponseDto.ReplyPreviewDto> readReply(
            @PathVariable Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDto(reply));
    }

    @GetMapping("posts/{postId}/replies")
    public BaseResponse<ReplyResponseDto.ReplyPreviewListDto> readRepliesByPost(
            @PathVariable Long postId) {
        List<Reply> replies = replyService.readRepliesByPost(postId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDto(replies));
    }

    @GetMapping("replies")
    public BaseResponse<ReplyResponseDto.ReplyPreviewListDto> readReplies() {
        List<Reply> postList = replyService.readReplies();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDto(postList));
    }

    @DeleteMapping("replies/{replyId}")
    public BaseResponse<String> deleteReply(
            @PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }
}
