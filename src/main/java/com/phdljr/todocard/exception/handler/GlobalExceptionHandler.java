package com.phdljr.todocard.exception.handler;

import com.phdljr.todocard.exception.dto.ExceptionResponseDto;
import com.phdljr.todocard.exception.type.CustomException;
import com.phdljr.todocard.exception.type.NotFoundCardException;
import com.phdljr.todocard.exception.type.NotMatchUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundCardException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundCardException() {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(CustomException.NOT_FOUND_CARD.toDto());
    }

    @ExceptionHandler(NotMatchUserException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotMatchUserException() {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomException.NOT_MATCH_USER.toDto());
    }

}
