package com.phdljr.todocard.repository;

import com.phdljr.todocard.entity.Card;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByTitle(String title);
}
