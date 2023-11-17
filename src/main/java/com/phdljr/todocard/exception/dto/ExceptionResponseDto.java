package com.phdljr.todocard.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionResponseDto {

    private int statusCode;
    private String message;

    @Builder
    public ExceptionResponseDto(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
