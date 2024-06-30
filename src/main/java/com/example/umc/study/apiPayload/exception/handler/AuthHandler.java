package com.example.umc.study.apiPayload.exception.handler;

import com.example.umc.study.apiPayload.code.BaseErrorCode;
import com.example.umc.study.apiPayload.exception.GeneralException;

public class AuthHandler extends GeneralException {
    public AuthHandler(BaseErrorCode code) {
        super(code);
    }
}