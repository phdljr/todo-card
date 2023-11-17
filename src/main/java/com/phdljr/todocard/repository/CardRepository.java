package com.phdljr.todocard.repository;

import com.phdljr.todocard.entity.Card;
import com.phdljr.todocard.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
