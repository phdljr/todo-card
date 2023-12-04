package com.phdljr.todocard.dto.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardRequestDtoTest {

    @Test
    public void dtoTest() {
        // given
        CardRequestDto cardRequestDto = new CardRequestDto("title", "content");

        // when - then
        assertThat(cardRequestDto.getTitle()).isEqualTo("title");
        assertThat(cardRequestDto.getContent()).isEqualTo("content");
    }
}