package com.example.umc.study.controller;

import com.example.umc.study.service.TestService;
import com.example.umc.study.apiPayload.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestController는 ResponseBody와 controller를 합친 것이라고 보면 됨.
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

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