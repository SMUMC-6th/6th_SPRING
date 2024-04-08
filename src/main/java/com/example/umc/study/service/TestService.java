package com.example.umc.study.service;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.TestHandler;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void faildTest(){
        if(1==1){
            throw new TestHandler(ErrorStatus._BAD_REQUEST);
        }
    }
    public void unauthorized(){
        if(1 == 1){
            throw new TestHandler(ErrorStatus._UNAUTHORIZED);
        }
    }
}
