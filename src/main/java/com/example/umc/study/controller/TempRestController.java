package com.example.umc.study.controller;

import com.example.umc.study.apiPayload.BaseResponse;
import com.example.umc.study.converter.TempConverter;
import com.example.umc.study.dto.TempResponse;
import com.example.umc.study.service.tempService.TempQueryService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public BaseResponse<TempResponse.TempTestDTO> testAPI() {
        return BaseResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public BaseResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
       tempQueryService.CheckFlag(flag);
       return BaseResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}
