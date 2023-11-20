package com.phdljr.todocard.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponseDto {

    private long id;
    private String username;
    private String email;
}
