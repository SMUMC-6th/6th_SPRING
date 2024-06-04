package com.example.umc.study.apiPayload.code.status;

import com.example.umc.study.apiPayload.code.BaseErrorCode;
import com.example.umc.study.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    //user 에러
    _NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER400", "user을 찾을 수 없습니다"),

    //post에러
    _NOT_FOUND_POST(HttpStatus.NOT_FOUND, "POST400", "게시글을 찾을 수 없습니다"),

    //reply에러
    _NOT_FOUND_REPLY(HttpStatus.NOT_FOUND, "REPLY400", "댓글을 찾을 수 없습니다"),

    //테스트 에러
    _TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이것은 테스트");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
