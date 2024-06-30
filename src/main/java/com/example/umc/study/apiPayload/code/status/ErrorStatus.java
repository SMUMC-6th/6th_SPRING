package com.example.umc.study.apiPayload.code.status;

import com.example.umc.study.apiPayload.code.BaseErrorCode;
import com.example.umc.study.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 기본 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // user 에러
    _NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER400", "user를 찾을 수 없습니다."),

    // post 에러
    _NOT_FOUND_POST(HttpStatus.NOT_FOUND, "POST400", "post를 찾을 수 없습니다."),

    // reply 에러
    _NOT_FOUND_REPLY(HttpStatus.NOT_FOUND, "REPLY400", "reply를 찾을 수 없습니다."),

    // token 에러
    _AUTH_EXPIRE_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN401_0", "토큰이 만료되었습니다."),
    _AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN401_1", "토큰이 부적절합니다."),
    _TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "TOKEN404_0", "토큰이 존재하지 않습니다."),

    // 인증관련
    _AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "AUTH401_0", "인증에 실패했습니다."),
    _BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "AUTH401_1", "비밀번호를 잘못 입력했습니다."),
    _ACCOUNT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH401_4", "아이디를 잘못 입력했습니다. 회원가입 후 이용해주세요."),

    // 파싱 에러
    _PARSING_ERROR(HttpStatus.BAD_REQUEST, "PARSE400", "데이터 파싱 중 오류가 발생했습니다.");

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
