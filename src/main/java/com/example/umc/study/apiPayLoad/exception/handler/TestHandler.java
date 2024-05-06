package com.example.umc.study.apiPayLoad.exception.handler;

import com.example.umc.study.apiPayLoad.code.BaseErrorCode;
import com.example.umc.study.apiPayLoad.exception.GeneralException;

public class TestHandler extends GeneralException {
    public TestHandler(BaseErrorCode code) {
        super(code);
    }
}
