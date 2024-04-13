package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;
import com.example.umc.study.service.ReplyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/api/v1/replys")
    public BaseResponse<ReplyResponseDTO.ReplyResultDTO> createReply(@RequestBody ReplyRequestDTO.ReplyDTO replyDTO) {
        Reply reply = replyService.createReply(replyDTO);
        return BaseResponse.onSuccess(ReplyConverter.toReplyResultDTO(reply));
    }

    @GetMapping("/api/v1/replys/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReply(@PathVariable Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @GetMapping("/api/v1/replys")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readReply() {
        List<Reply> replyList = replyService.readReply();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replyList));
    }

    @DeleteMapping("/api/v1/replys/{replyId}")
    public void deleteReply(@PathVariable Long replyId) { replyService.deleteReply(replyId); }
}
