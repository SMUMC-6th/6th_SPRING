package com.example.umc.study.service;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.PostHandler;
import com.example.umc.study.apiPayload.exception.handler.ReplyHandler;
import com.example.umc.study.apiPayload.exception.handler.UserHandler;
import com.example.umc.study.converter.ReplyConverter;
import com.example.umc.study.domain.Post;
import com.example.umc.study.domain.Reply;
import com.example.umc.study.domain.User;
import com.example.umc.study.dto.ReplyRequestDTO;
import com.example.umc.study.repository.PostRepository;
import com.example.umc.study.repository.ReplyRepository;
import com.example.umc.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
<<<<<<< Updated upstream
public class ReplyServiceImpl implements ReplyService{
=======
@Transactional
public class ReplyServiceImpl implements ReplyService {
>>>>>>> Stashed changes

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

//    @Transactional
//    @Override
//    public Reply createReply(ReplyRequestDTO.JoinDTO joinDTO) {
//        Reply reply = ReplyConverter.toReply(joinDTO);
//        return replyRepository.save(reply);
//    }

    @Transactional(readOnly = true)
    @Override
    public Reply readReply(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reply> readReplys() {
        return replyRepository.findAll();
    }

    @Transactional
    @Override
    public void deletePost(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyHandler(ErrorStatus._NOT_FOUND_REPLY));
        replyRepository.delete(reply);
    }

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO, Long userId, Long postId) {
        Reply reply = ReplyConverter.toReply(createReplyDTO);
        User user = userRepository.findById(userId).orElseThrow(()-> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        reply.setUser(user);
        reply.setPost(post);
        replyRepository.save(reply);
        return reply;
    }

    @Override
    public List<Reply> readRepliesByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        return replyRepository.findAllByPost(post);
    }
}
