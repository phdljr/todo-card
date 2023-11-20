package com.phdljr.todocard.dto.request;

import jakarta.validation.constraints.Email;
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

    @Size(min = 4, max = 10, message = "username의 길이가 4~10 사이여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "username은 소문자 알파벳과 숫자만 입력가능합니다.")
    private String username;

    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @Size(min = 8, max = 15, message = "비밀번호의 길이가 8~15 사이여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "비밀번호는 영문자와 숫자만 입력가능합니다.")
    private String password;
}
