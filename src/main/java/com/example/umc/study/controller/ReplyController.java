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
<<<<<<< Updated upstream
=======
@RequestMapping("/api/v1")
@CrossOrigin("*")
>>>>>>> Stashed changes
public class ReplyController {

    private final ReplyService replyService;

<<<<<<< Updated upstream
    @PostMapping("/api/v1/replys")
    public BaseResponse<ReplyResponseDTO.JoinResultDTO> createReply(@RequestBody ReplyRequestDTO.JoinDTO joinDTO) {
        Reply reply = replyService.createReply(joinDTO);
        return BaseResponse.onSuccess(ReplyConverter.toJoinResultDTO(reply));
=======
//    @PostMapping("/replys")
//    public BaseResponse<ReplyResponseDTO.JoinResultDTO> createReply(@RequestBody ReplyRequestDTO.JoinDTO joinDTO) {
//        Reply reply = replyService.createReply(joinDTO);
//        return BaseResponse.onSuccess(ReplyConverter.toJoinResultDTO(reply));
//
//    }
>>>>>>> Stashed changes

    @PostMapping("/users/{userId}/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.CreateReplyResultDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO createReplyDTO, @PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        Reply reply = replyService.createReply(createReplyDTO, userId, postId);
        return BaseResponse.onSuccess(ReplyConverter.toCreateReplyResultDTO(reply));
    }
<<<<<<< Updated upstream
    @GetMapping("/api/v1/replys/{replyId}")
=======

    @GetMapping("/replys/{replyId}")
>>>>>>> Stashed changes
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
    @GetMapping("/posts/{postId}/replies")
    public BaseResponse<ReplyResponseDTO.ReplyPreviewListDTO> readRepliesByPost(@PathVariable("postId") Long postId) {
        List<Reply> replies = replyService.readRepliesByPost(postId);
        return BaseResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

}
