package com.phdljr.todocard.controller;

import com.phdljr.todocard.dto.SignUpRequestDto;
import com.phdljr.todocard.dto.SignUpResponseDto;
import com.phdljr.todocard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto responseDto = userService.signup(signUpRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
