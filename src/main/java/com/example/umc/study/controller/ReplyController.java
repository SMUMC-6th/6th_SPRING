package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.PostConverter;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.converter.UserConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.dto.PostRequestDTO;
import com.example.umc.study.dto.PostResponseDTO;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.dto.ReplyResponseDTO;
import com.example.umc.study.service.ReplyService;
import com.example.umc.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin
public class ReplyController {
    private final ReplyService replyService;
    @PostMapping("/users/{userId}/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.JoinReplyResultDTO> createReply(@PathVariable Long userId, @PathVariable Long postId, @RequestBody ReplyRequestDTO.JoinReplyDTO joinReplyDTO) {
        Reply reply = replyService.createReply(userId, postId, joinReplyDTO);
        return BaseResponse.onSuccess(ReplyConverter.toJoinReplyResultDTO(reply));
    }


    @GetMapping("/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> readReply(@PathVariable Long replyId) {
        Reply reply = replyService.readReply(replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

/*
    @DeleteMapping("/api/v1/replies/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }
*/

    @DeleteMapping("/replies/{replyId}")
    public BaseResponse<String> deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }


    @GetMapping("/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readReplies() {
        List<Reply> replyList = replyService.readReplies();
        return BaseResponse.onSuccess(ReplyConverter.toJoinReplyPreviewListDTO(replyList));
    }

    @GetMapping("/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readRepliesByPost(@PathVariable Long postId) {
        List<Reply> replyList = replyService.readRepliesByPost(postId);
        return BaseResponse.onSuccess(ReplyConverter.toJoinReplyPreviewListDTO(replyList));
    }

    @PatchMapping ("/replies/{replyId}")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewDTO> updateReply(@RequestBody ReplyRequestDTO.UpdateReplyDTO updateReplyDTO,
                                                                      @PathVariable Long replyId) {
        Reply reply = replyService.updateReply(updateReplyDTO, replyId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }
}
