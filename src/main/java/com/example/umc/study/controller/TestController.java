package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //responsebody와 controller 어노테이션을 합친 것
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;  //testcontroller에 testservice를 생성사 주입 , @RequiredArgsConstructor가 필요함

    //모든 코드를 BaseResponse로 리턴함으로 응답을 통일하여서 프론트와 통신을 더 용이하게함,
    //성공 응답에 새로운 코드를 적고 싶을때 SuccessStatus enum을 계속 추가해준다
    //성공 응답에 새로운 코드를 적고싶을때 SuccessStatus enum을 계속 추가해준다
    @GetMapping("/") // get메서드로, /경로로 응답을 받는다는 의미.
    public BaseResponse<String> test() {
        return BaseResponse.onSuccess("성공");
    }

    //테스트 코드
    //failed 경로를 통해 들어가면 testService의 failTest가 실행되고 failedTest에서는 TestHandler가 던져지는데
    //그러면 TestHandler의 생성자가 실행됨(super은 부모의 생성자를 호출)
    // GeneralException은 RuntimeException을 상속 받기 때문에 RuntimeException이 던저지게됨
    //그러면 RestController가 이를 감지하여 관련 함수가 실행되며 에러 뜸
    @GetMapping("/failed")
    public BaseResponse<String> failedTest() {
        testService.failedTest();
        return BaseResponse.onSuccess("성공");
    }



}
