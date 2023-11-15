package com.phdljr.todocard.service;

import com.phdljr.todocard.dto.SignUpRequestDto;
import com.phdljr.todocard.dto.SignUpResponseDto;

public interface UserService {

    SignUpResponseDto signup(SignUpRequestDto requestDto);
}
