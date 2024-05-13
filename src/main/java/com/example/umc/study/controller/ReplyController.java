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
@RequestMapping("/api/v1")
@CrossOrigin("*")

public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/users/{userId}/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.CreateReplyResultDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO createReplyDTO, @PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        Reply reply = replyService.createReply(createReplyDTO, userId, postId);
        return BaseResponse.onSuccess(ReplyConverter.toCreateReplyResultDTO(reply));
    }

    @GetMapping("/replys/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @GetMapping("/replys")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readReplys() {
        List<Reply> replyList = replyService.readReplys();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replyList));
    }

    @DeleteMapping("/replys/{replyId}")
    public void deleteReply(@PathVariable("replyId") Long replyId) {
        replyService.deletePost(replyId);
    }
    @GetMapping("/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readRepliesByPost(@PathVariable("postId") Long postId) {
        List<Reply> replies = replyService.readRepliesByPost(postId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

}
