package com.example.umc.study.apiPayload.exception.handler;

import com.example.umc.study.apiPayload.code.BaseErrorCode;
import com.example.umc.study.apiPayload.exception.GeneralException;

public class TestHandler extends GeneralException {
    public TestHandler(BaseErrorCode code) {
        super(code);
    }
}
