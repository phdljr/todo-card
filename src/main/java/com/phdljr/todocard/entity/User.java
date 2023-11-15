package com.phdljr.todocard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "TB_USER")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 10, message = "길이가 4~10 사이여야 합니다.")
    @Pattern(regexp = "[a-z0-9]", message = "소문자 알파벳과 숫자만 입력가능합니다.")
    @Column(nullable = false)
    private String username;

    @Size(min = 8, max = 15, message = "길이가 8~15 사이여야 합니다.")
    @Pattern(regexp = "[a-zA-Z0-9]", message = "영문자와 숫자만 입력가능합니다.")
    @Column(nullable = false)
    private String password;

    @Email(message = "이메일 형식이 아닙니다.")
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
