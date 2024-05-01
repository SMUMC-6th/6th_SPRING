package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.dto.request.ReplyRequestDTO;
import com.example.umc.study.dto.response.ReplyResponseDTO;
import com.example.umc.study.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{userId}/{postId}")
    public BaseResponse<ReplyResponseDTO.CreateReplyResultDTO> createReply(@PathVariable Long userId, @PathVariable Long postId, @RequestBody ReplyRequestDTO.CreateReplyDTO createReplyDTO) {
        Reply reply = replyService.createReply(userId,postId,createReplyDTO);
        return BaseResponse.onSuccess(ReplyConverter.toCreateReplyResultDTO(reply));
    }

    // replyId로 댓글 불러오기
    @GetMapping("/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReply(@PathVariable Long replyId){
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    // postId로 댓글 불러오기
    @GetMapping("/posts/{postId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReplyByPostId(@PathVariable Long postId){
        List<Reply> replyList = replyService.findAllByPost(postId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replyList));
    }

    //댓글 전체 불러오기
    @GetMapping("/list")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readReplies(){
        List<Reply> replyList = replyService.readReplies();
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replyList));
    }

    // 댓글 삭제하기
    @DeleteMapping("/delete/{replyId}")
    public void deleteReply(@PathVariable Long replyId){replyService.deleteReply(replyId);}
}


