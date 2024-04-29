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

    @PostMapping("/api/v1/replys")
    public BaseResponse<ReplyResponseDTO.JoinResultDTO> createReply(@RequestBody ReplyRequestDTO.JoinDTO joinDTO) {
        Reply reply = replyService.createReply(joinDTO);
        return BaseResponse.onSuccess(ReplyConverter.toJoinResultDTO(reply));

    }
    @GetMapping("/api/v1/replys/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @GetMapping("/api/v1/replys")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readReplys() {
        List<Reply> replyList = replyService.readReplys();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replyList));
    }

    @DeleteMapping("/api/v1/replys/{replyId}")
    public void deleteReply(@PathVariable("replyId") Long replyId) {
        replyService.deletePost(replyId);
    }

}
