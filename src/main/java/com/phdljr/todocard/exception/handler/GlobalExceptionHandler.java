package com.phdljr.todocard.exception.handler;

import com.phdljr.todocard.exception.dto.ExceptionResponseDto;
import com.phdljr.todocard.exception.dto.ValidExceptionResponseDto;
import com.phdljr.todocard.exception.type.CustomException;
import com.phdljr.todocard.exception.type.DuplicateEmailException;
import com.phdljr.todocard.exception.type.DuplicateUsernameException;
import com.phdljr.todocard.exception.type.NotFoundCardException;
import com.phdljr.todocard.exception.type.NotFoundCommentException;
import com.phdljr.todocard.exception.type.NotMatchUserException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidExceptionResponseDto> handleMethodArgumentNotValidException(
        BindingResult bindingResult) {

        ValidExceptionResponseDto responseDto
            = new ValidExceptionResponseDto(400, bindingResult);

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(responseDto);
    }

    @ExceptionHandler(NotFoundCardException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundCardException() {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(CustomException.NOT_FOUND_CARD.toDto());
    }

    @ExceptionHandler(NotFoundCommentException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundCommentException() {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomException.NOT_FOUND_COMMENT.toDto());
    }

    @ExceptionHandler(NotMatchUserException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotMatchUserException() {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomException.NOT_MATCH_USER.toDto());
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateUsernameException() {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomException.DUPLICATE_USERNAME.toDto());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateEmailException() {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomException.DUPLICATE_EMAIL.toDto());
    }

}
