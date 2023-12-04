package com.phdljr.todocard.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.phdljr.todocard.entity.User;
import com.phdljr.todocard.entity.UserRole;
import com.phdljr.todocard.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
public class TransactionalTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    @Transactional
    public void test() {
        User user = User.builder()
            .username("username")
            .role(UserRole.USER)
            .email("test@test.com")
            .password("123123123")
            .build();
        userRepository.save(user);

        try {
            testPropagation();
        } catch (RuntimeException e){

        }

    }

    @Transactional(propagation = Propagation.NESTED)
    public void testPropagation(){
        User user = User.builder()
            .username("username2")
            .role(UserRole.USER)
            .email("test2@test.com")
            .password("1231231232")
            .build();
        userRepository.save(user);

        throw new RuntimeException();
    }

    @Order(2)
    @Test
    public void check() {
        Optional<User> findUser = userRepository.findByUsername("username");
        assertThat(findUser).isEmpty();
    }

}
