package com.example.umc.study.service;

import com.example.umc.study.apiPayLoad.code.status.ErrorStatus;
import com.example.umc.study.apiPayLoad.exception.handler.TestHandler;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void failedTest() {
        if (true) {
            throw new TestHandler(ErrorStatus._BAD_REQUEST);
        }
    }

}