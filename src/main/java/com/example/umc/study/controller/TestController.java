package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestController는 ResponseBody + controller
@RequiredArgsConstructor
public class TestController {
//    @GetMapping("/") // "/"경로로 응답을 받음. 아래처럼 주소로 직접 쳐서 들어오는 경우는 get (post나 put, patch delete도 있다.)
//    public BaseResponse<String> test() {
//        return BaseResponse.onSuccess("성공!");
//    }
    private final TestService testService;

    @GetMapping("/failed")
    public BaseResponse<String> failedTest() {
        testService.failedTest(); // failed 경로로 들어가서 testService.failedTest() 실행
        return BaseResponse.onSuccess("성공!");
    }
}