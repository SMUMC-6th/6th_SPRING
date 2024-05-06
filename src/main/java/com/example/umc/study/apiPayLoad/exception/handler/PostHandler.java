package com.example.umc.study.apiPayLoad.exception.handler;

import com.example.umc.study.apiPayLoad.code.BaseErrorCode;
import com.example.umc.study.apiPayLoad.exception.GeneralException;

public class PostHandler extends GeneralException {
    public PostHandler(BaseErrorCode code) {
        super(code);
    }
}
