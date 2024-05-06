package com.example.umc.study.apiPayload.exception.handler;

import com.example.umc.study.apiPayload.code.BaseErrorCode;
import com.example.umc.study.apiPayload.exception.GeneralException;

public class ReplyHandler extends GeneralException {
    public ReplyHandler(BaseErrorCode code) {
        super(code);
    }
}
