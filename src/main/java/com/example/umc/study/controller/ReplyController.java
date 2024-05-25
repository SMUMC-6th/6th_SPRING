package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;
import com.example.umc.study.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // DI
@RequestMapping("/api/v1")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/users/{userId}/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.CreateReplyResultDTO> createReply(@PathVariable Long userId, @PathVariable Long postId, @RequestBody ReplyRequestDTO.CreateReplyDTO createReplyDTO) {
        Reply reply = replyService.createReply(userId, postId, createReplyDTO);
        return BaseResponse.onSuccess(ReplyConverter.toCreateReplyResultDTO(reply));
    }

    @GetMapping("/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReply(@PathVariable Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @GetMapping("/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readReplies() {
        List<Reply> replies = replyService.readReplies();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    @DeleteMapping("/replies/{replyId}")
    public BaseResponse<String> deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return BaseResponse.onSuccess("삭제되었습니다.");
    }

    //findAllByPost
    @GetMapping("/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readRepliesByPost(@PathVariable Long postId) {
        List<Reply> replies = replyService.readRepliesByPost(postId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    //수정
    @PatchMapping("/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReply(@RequestBody ReplyRequestDTO.UpdateReplyDTO updateReplyDTO, @PathVariable Long replyId) {
        Reply reply = replyService.updateReply(updateReplyDTO, replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

}
