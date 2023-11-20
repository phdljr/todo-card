package com.phdljr.todocard.exception.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
public class ValidExceptionResponseDto {

    private final int statusCode;
    private final List<String> messages;

    public ValidExceptionResponseDto(final int statusCode, final BindingResult bindingResult) {
        this.statusCode = statusCode;
        messages = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            messages.add(fieldError.getDefaultMessage());
        }
    }
}
