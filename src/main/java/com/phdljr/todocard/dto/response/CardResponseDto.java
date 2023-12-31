package com.phdljr.todocard.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardResponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private boolean isFinished;
    private boolean isHidden;
    private boolean isPrivate;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder.Default
    private List<CommentResponseDto> comments = new ArrayList<>();
}
