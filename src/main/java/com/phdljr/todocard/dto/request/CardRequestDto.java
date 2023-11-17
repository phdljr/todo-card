package com.phdljr.todocard.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardRequestDto {
    private String title;
    private String content;
}
