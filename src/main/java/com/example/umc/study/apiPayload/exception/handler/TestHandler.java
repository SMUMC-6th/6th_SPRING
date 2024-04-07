package com.example.umc.study.apiPayload.exception.handler;

import com.example.umc.study.apiPayload.code.BaseErrorCode;
import com.example.umc.study.apiPayload.exception.GeneralException;

//만약 유저와 관련된 핸들러라면 UserHandler post와 관련된 핸들러라면 PostHandler
public class TestHandler extends GeneralException {

    public TestHandler(BaseErrorCode code) {
        super(code);

    }
}
