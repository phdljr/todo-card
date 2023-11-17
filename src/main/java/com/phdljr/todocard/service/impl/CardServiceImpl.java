package com.phdljr.todocard.service.impl;

import com.phdljr.todocard.dto.request.CardRequestDto;
import com.phdljr.todocard.dto.response.CardResponseDto;
import com.phdljr.todocard.dto.response.CardsResponseDto;
import com.phdljr.todocard.entity.Card;
import com.phdljr.todocard.entity.User;
import com.phdljr.todocard.exception.NotFoundCardException;
import com.phdljr.todocard.exception.NotMatchUser;
import com.phdljr.todocard.repository.CardRepository;
import com.phdljr.todocard.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    @Transactional(readOnly = true)
    public CardResponseDto getCard(final Long cardId) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NotFoundCardException("할일 카드를 찾지 못하였습니다."));
        return card.toCardResponseDto();
    }

    // TODO
    @Override
    public List<CardsResponseDto> getCards() {
        List<Card> cards = cardRepository.findAll();
        // 1. username별로 나눈다
        // 2. 나뉜 카드를 dto로 만든다.
        return null;
    }

    @Override
    @Transactional
    public CardResponseDto updateCard(final Long cardId, final CardRequestDto cardRequestDto,
        final User user) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NotFoundCardException("할일 카드를 찾지 못하였습니다."));

        if (!card.getUser().getId().equals(user.getId())) {
            throw new NotMatchUser("회원님의 할일 카드가 아닙니다.");
        }

        card.update(cardRequestDto);
        return card.toCardResponseDto();
    }

    @Override
    public CardResponseDto createCard(final CardRequestDto cardRequestDto,
        final User user) {
        Card card = Card.builder()
            .title(cardRequestDto.getTitle())
            .content(cardRequestDto.getTitle())
            .user(user)
            .isHidden(false)
            .isFinished(false)
            .build();
        card = cardRepository.save(card);
        return card.toCardResponseDto();
    }

    @Override
    public Long deleteCard(final Long cardId, final User user) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NotFoundCardException("할일 카드를 찾지 못하였습니다."));

        if (!card.getUser().getId().equals(user.getId())) {
            throw new NotMatchUser("회원님의 할일 카드가 아닙니다.");
        }

        cardRepository.delete(card);
        return cardId;
    }

    @Override
    @Transactional
    public CardResponseDto toggleHideCard(final Long cardId, final User user) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NotFoundCardException("할일 카드를 찾지 못하였습니다."));

        if (!card.getUser().getId().equals(user.getId())) {
            throw new NotMatchUser("회원님의 할일 카드가 아닙니다.");
        }

        card.toggleHide();
        return card.toCardResponseDto();
    }

    @Override
    @Transactional
    public CardResponseDto toggleFinishCard(final Long cardId, final User user) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NotFoundCardException("할일 카드를 찾지 못하였습니다."));

        if (!card.getUser().getId().equals(user.getId())) {
            throw new NotMatchUser("회원님의 할일 카드가 아닙니다.");
        }

        card.toggleFinish();
        return card.toCardResponseDto();
    }
}
