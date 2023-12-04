package com.phdljr.todocard.dto.request;

import lombok.Getter;

@Getter
public class CardRequestDto {

    private String title;
    private String content;

    public CardRequestDto(final String title, final String content) {
        this.title = title;
        this.content = content;
    }
}
