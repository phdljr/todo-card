package com.phdljr.todocard.repository;

import com.phdljr.todocard.entity.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByTitleContainsOrderByUserAscCreatedAtDesc(String title);

    List<Card> findAllByOrderByUserAscCreatedAtDesc();
}
