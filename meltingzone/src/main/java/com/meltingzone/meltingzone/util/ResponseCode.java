package com.meltingzone.meltingzone.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(HttpStatus.OK, true, "성공"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "해당 계정이 존재하지 않습니다."),
    USER_DUPLICATED(HttpStatus.CONFLICT, false, "이미 해당 계정의 유저가 존재합니다."),

    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, false, "토큰이 존재하지 않습니다."),
    TOKEN_INVALID(HttpStatus.BAD_REQUEST, false, "액세스 토큰이 만료되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String message;
}
