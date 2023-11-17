package com.phdljr.todocard.service;

import com.phdljr.todocard.dto.request.SignUpRequestDto;
import com.phdljr.todocard.dto.response.SignUpResponseDto;

public interface UserService {

    SignUpResponseDto signup(SignUpRequestDto requestDto);
}
