package com.phdljr.todocard.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {

    private Long id;
    private String username;
    private String content;
    private Long card_id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
