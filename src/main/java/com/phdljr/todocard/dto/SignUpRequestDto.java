package com.phdljr.todocard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    private String username;
    private String email;
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}
