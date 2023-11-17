package com.phdljr.todocard.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class CardsResponseDto {

    private String username;
    private List<CardResponseDto> cards;
}
