package com.example.umc.study.Service;

import com.example.umc.study.apiPayload.code.status.ErrorStatus;
import com.example.umc.study.apiPayload.exception.handler.TestHandler;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void failedTest() {
        if (1==1) {
            throw new TestHandler(ErrorStatus._BAD_REQUEST);
        }
    }
}
