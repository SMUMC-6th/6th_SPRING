package com.example.umc.study.apiPayLoad.exception.handler;

import com.example.umc.study.apiPayLoad.code.BaseErrorCode;
import com.example.umc.study.apiPayLoad.exception.GeneralException;

public class AuthHandler extends GeneralException {

    public AuthHandler(BaseErrorCode code) {
        super(code);
    }
}
