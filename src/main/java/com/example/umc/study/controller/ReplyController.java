package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.request.ReplyRequestDTO;
import com.example.umc.study.dto.response.ReplyResponseDTO;
import com.example.umc.study.service.ReplyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.CreateReplyResultDTO> createReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO) {
        Reply reply = replyService.createReply(createReplyDTO);
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
        return BaseResponse.onSuccess("삭제에 성공하였습니다.");
    }

    @GetMapping("/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> findAllByPost(@PathVariable Long postId) {
        List<Reply> replies = replyService.findAllByPost(postId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }
}
