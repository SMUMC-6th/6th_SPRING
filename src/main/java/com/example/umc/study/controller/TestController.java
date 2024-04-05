package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        testService.faildTest();
        return BaseResponse.onSuccess("성공!");
    }
    @GetMapping("/unauthorized")
    public BaseResponse<String> unauthorized() {
        testService.unauthorized();
        return BaseResponse.onSuccess("성공!");
    }


}
