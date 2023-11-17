package com.phdljr.todocard.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Size(min = 8, max = 15, message = "길이가 8~15 사이여야 합니다.")
    @Pattern(regexp = "[a-zA-Z0-9]", message = "영문자와 숫자만 입력가능합니다.")
    private String password;
}
