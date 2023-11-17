package com.phdljr.todocard.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpResponseDto {
    private long id;
    private String username;
    private String email;
}
