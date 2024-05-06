package com.example.umc.study.apiPayload.exception.handler;

import com.example.umc.study.apiPayload.code.BaseErrorCode;
import com.example.umc.study.apiPayload.exception.GeneralException;

public class PostHandler extends GeneralException {
    public PostHandler(BaseErrorCode code) {
        super(code);
    }
}
