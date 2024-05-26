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
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/users/{userId}/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.JoinResultDTO> createReply(@PathVariable Long userId,
                                                                    @PathVariable Long postId,
                                                                    @RequestBody ReplyRequestDTO.JoinDTO joinDTO) {
        Reply reply = replyService.createReply(userId, postId, joinDTO);
        return BaseResponse.onSuccess(ReplyConverter.toJoinResultDTO(reply));
    }

    @GetMapping("/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReply(@PathVariable Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @GetMapping("/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readReplies() {
        List<Reply> replyList = replyService.readReplies();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replyList));
    }

    @GetMapping("/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readPostReplies(@PathVariable Long postId) {
        List<Reply> replies = replyService.readRepliesByPost(postId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    @DeleteMapping("/replies/{replyId}")
    public BaseResponse<String> deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }

    @PatchMapping("/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyService.updateReply(replyId, dto);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }
}
