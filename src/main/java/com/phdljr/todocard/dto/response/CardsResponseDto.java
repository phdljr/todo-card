package com.phdljr.todocard.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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
