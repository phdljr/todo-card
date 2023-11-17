package com.phdljr.todocard.entity;

import com.phdljr.todocard.repository.CardRepository;
import com.phdljr.todocard.repository.CommentRepository;
import com.phdljr.todocard.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class InsertEntityTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init(){
        commentRepository.deleteAll();
        cardRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void insertEntity(){
        User user = User.builder()
            .username("username")
            .role(UserRole.USER)
            .email("test@test.com")
            .password(passwordEncoder.encode("123456789101112"))
            .build();
        userRepository.save(user);

        Card card = Card.builder()
            .title("title")
            .user(user)
            .content("content")
            .isHidden(false)
            .isFinished(false)
            .build();
        cardRepository.save(card);

        Comment comment = Comment.builder()
            .content("content")
            .card(card)
            .user(user)
            .build();
        commentRepository.save(comment);
    }
}
