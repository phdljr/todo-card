package com.phdljr.todocard.entity;

import com.phdljr.todocard.dto.request.CardRequestDto;
import com.phdljr.todocard.dto.response.CardResponseDto;
import com.phdljr.todocard.dto.response.CardResponseDto.CardResponseDtoBuilder;
import com.phdljr.todocard.dto.response.CommentResponseDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CARD")
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private boolean isFinished;

    @Column
    private boolean isHidden;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public CardResponseDto toCardResponseDto() {
        return CardResponseDto.builder()
            .id(id)
            .username(user.getUsername())
            .title(title)
            .content(content)
            .createdAt(getCreatedAt())
            .modifiedAt(getModifiedAt())
            .isFinished(isFinished)
            .isHidden(isHidden)
            .comments(comments.stream().map(Comment::toDto).toList())
            .build();
    }

    public void update(final CardRequestDto cardRequestDto) {
        this.title = cardRequestDto.getTitle();
        this.content = cardRequestDto.getContent();
    }

    public void toggleHide() {
        this.isHidden = !this.isHidden;
    }

    public void toggleFinish() {
        this.isFinished = !this.isFinished;
    }
}
