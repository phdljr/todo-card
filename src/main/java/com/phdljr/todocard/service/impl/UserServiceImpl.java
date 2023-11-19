package com.phdljr.todocard.service.impl;

import com.phdljr.todocard.dto.request.SignUpRequestDto;
import com.phdljr.todocard.dto.response.SignUpResponseDto;
import com.phdljr.todocard.entity.User;
import com.phdljr.todocard.entity.UserRole;
import com.phdljr.todocard.exception.type.DuplicateEmailException;
import com.phdljr.todocard.exception.type.DuplicateUsernameException;
import com.phdljr.todocard.repository.UserRepository;
import com.phdljr.todocard.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Value("${custom.admin.token}")
//    private String ADMIN_TOKEN;

    public SignUpResponseDto signup(SignUpRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new DuplicateUsernameException();
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new DuplicateEmailException();
        }

        // 사용자 ROLE 확인
//        UserRole role = UserRole.USER;
//        if (requestDto.isAdmin()) {
//            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRole.ADMIN;
//        }

        // 사용자 등록
        User user = User.builder()
            .username(username)
            .password(password)
            .email(email)
            .role(UserRole.USER)
            .build();
        user = userRepository.save(user);

        return SignUpResponseDto.builder()
            .id(user.getId())
            .username(username)
            .email(email)
            .build();
    }
}