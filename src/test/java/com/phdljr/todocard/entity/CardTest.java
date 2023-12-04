package com.phdljr.todocard.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.phdljr.todocard.dto.request.CardRequestDto;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void update() {
        // given
        String title = "update title";
        String content = "update content";
        Card card = Card.builder().title("test title").content("test content").build();

        // when
        card.update(new CardRequestDto(title, content));

        // then
        assertThat(card.getTitle()).isEqualTo(title);
        assertThat(card.getContent()).isEqualTo(content);
    }
}