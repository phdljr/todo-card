package com.phdljr.todocard.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpResponseDto {

    private String username;
    private String email;
}
