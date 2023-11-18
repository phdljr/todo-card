package com.phdljr.todocard.entity;

import com.phdljr.todocard.dto.response.CommentResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_COMMENT")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Builder
    public Comment(final String content, final User user, final Card card) {
        this.content = content;
        this.user = user;
        this.card = card;
    }

    public void update(String content){
        this.content = content;
    }

    public CommentResponseDto toDto() {
        return CommentResponseDto.builder()
            .id(id)
            .username(user.getUsername())
            .card_id(card.getId())
            .content(content)
            .createdAt(getCreatedAt())
            .modifiedAt(getModifiedAt())
            .build();
    }
}
