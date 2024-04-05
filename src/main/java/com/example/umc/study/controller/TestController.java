package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // controller를 뜻하는 것 but RestController는 ResponseBody와 controller를 합친 것이라고 보면 됨.
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/") // 이 메소드는 Get으로 /경로로 응답을 받는다는 의미입니다. 밑에처럼 주소로 직접 쳐서 들어오는 경우 get입니다. post나 put, patch delete도 있습니다.
    public BaseResponse<String> test() {
        return BaseResponse.onSuccess("성공!");
    }
    @GetMapping("/failed")
    public BaseResponse<String> failedTest() {
        testService.failedTest();
        return BaseResponse.onSuccess("성공!");
    }
}