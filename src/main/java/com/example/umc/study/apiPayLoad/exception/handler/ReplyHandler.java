package com.example.umc.study.apiPayLoad.exception.handler;

import com.example.umc.study.apiPayLoad.code.BaseErrorCode;
import com.example.umc.study.apiPayLoad.exception.GeneralException;

public class ReplyHandler extends GeneralException {
    public ReplyHandler(BaseErrorCode code) {
        super(code);
    }
}
