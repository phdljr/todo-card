package com.phdljr.todocard.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CardResponseDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    private boolean isFinished;
    private boolean isHidden;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder.Default
    private List<CommentResponseDto> comments = new ArrayList<>();
}
