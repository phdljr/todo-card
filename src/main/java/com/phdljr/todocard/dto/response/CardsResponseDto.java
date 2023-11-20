package com.phdljr.todocard.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardsResponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private boolean isFinished;
    private boolean isHidden;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

