package com.phdljr.todocard.exception.type;

import com.phdljr.todocard.exception.dto.ExceptionResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomException {
    NOT_FOUND_CARD(404, "요청하신 할일 카드가 존재하지 않습니다."),
    NOT_MATCH_USER(400, "사용자의 정보가 일치하지 않습니다.");

    private final int statusCode;
    private final String message;

    public ExceptionResponseDto toDto() {
        return ExceptionResponseDto.builder()
            .statusCode(statusCode)
            .message(message)
            .build();
    }
}