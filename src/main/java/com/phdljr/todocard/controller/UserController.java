package com.phdljr.todocard.controller;

import com.phdljr.todocard.dto.request.SignUpRequestDto;
import com.phdljr.todocard.dto.response.SignUpResponseDto;
import com.phdljr.todocard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/sign-up")
    public ResponseEntity<SignUpResponseDto> signup(
        @RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto responseDto = userService.signup(signUpRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
