package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 테스트 컨트롤러 입니다!
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;


    @GetMapping("/")
    public BaseResponse<String> test() {
        return BaseResponse.onSuccess("성공!");
    }

    @GetMapping("/failed")
    public BaseResponse<String> failedTest() {
        testService.failedTest();
        return BaseResponse.onSuccess("성공!");
    }
}
