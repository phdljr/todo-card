package com.phdljr.todocard.entity;

import com.phdljr.todocard.dto.request.CardRequestDto;
import com.phdljr.todocard.dto.response.CardResponseDto;
import com.phdljr.todocard.dto.response.CardsResponseDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private boolean isFinished = false;

    @Column
    private boolean isHidden = false;

    @Column
    private boolean isPrivate = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private final List<Comment> comments = new ArrayList<>();

    @Builder
    public Card(final String title, final String content, final User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

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
            .isPrivate(isPrivate)
            .comments(comments.stream().map(Comment::toDto).toList())
            .build();
    }

    public CardsResponseDto toCardsResponseDto() {
        return CardsResponseDto.builder()
            .id(id)
            .username(user.getUsername())
            .title(title)
            .content(content)
            .createdAt(getCreatedAt())
            .modifiedAt(getModifiedAt())
            .isFinished(isFinished)
            .isHidden(isHidden)
            .isPrivate(isPrivate)
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

    public void togglePrivate() {
        this.isPrivate = !this.isPrivate;
    }
}
