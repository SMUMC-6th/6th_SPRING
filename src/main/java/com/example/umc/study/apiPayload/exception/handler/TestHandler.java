package com.example.umc.study.apiPayload.exception.handler;

import com.example.umc.study.apiPayload.exception.GeneralException;
import com.example.umc.study.apiPayload.code.BaseErrorCode;
public class TestHandler extends GeneralException {
    public TestHandler(BaseErrorCode code) {
        super(code); // super로 부모 생성자 호출 -> GeneralException
    }
}
