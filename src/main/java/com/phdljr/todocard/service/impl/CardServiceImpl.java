package com.phdljr.todocard.service.impl;

import com.phdljr.todocard.dto.request.CardRequestDto;
import com.phdljr.todocard.dto.response.CardResponseDto;
import com.phdljr.todocard.dto.response.CardsResponseDto;
import com.phdljr.todocard.entity.Card;
import com.phdljr.todocard.entity.User;
import com.phdljr.todocard.exception.type.NotFoundCardException;
import com.phdljr.todocard.exception.type.NotMatchUserException;
import com.phdljr.todocard.repository.CardRepository;
import com.phdljr.todocard.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    @Transactional(readOnly = true)
    public CardResponseDto getCard(final Long cardId) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(NotFoundCardException::new);
        return card.toCardResponseDto();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardsResponseDto> getCards(String searchTitle) {
        List<CardsResponseDto> list;
        if (StringUtils.hasText(searchTitle)) {
            list = cardRepository.findByTitleContainsOrderByUserAscCreatedAtDesc(searchTitle)
                .stream()
                .map(Card::toCardsResponseDto).toList();
        } else {
            list = cardRepository.findAllByOrderByUserAscCreatedAtDesc().stream()
                .map(Card::toCardsResponseDto).toList();
        }

        return list;
    }

    @Override
    @Transactional
    public CardResponseDto updateCard(final Long cardId, final CardRequestDto cardRequestDto,
        final User user) {
        Card card = findByIdWithUser(cardId, user);

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
            .build();
        card = cardRepository.save(card);
        return card.toCardResponseDto();
    }

    @Override
    public Long deleteCard(final Long cardId, final User user) {
        Card card = findByIdWithUser(cardId, user);

        cardRepository.delete(card);
        return cardId;
    }

    @Override
    @Transactional
    public CardResponseDto toggleHideCard(final Long cardId, final User user) {
        Card card = findByIdWithUser(cardId, user);

        card.toggleHide();
        return card.toCardResponseDto();
    }

    @Override
    @Transactional
    public CardResponseDto toggleFinishCard(final Long cardId, final User user) {
        Card card = findByIdWithUser(cardId, user);

        card.toggleFinish();
        return card.toCardResponseDto();
    }

    private Card findByIdWithUser(final Long cardId, final User user) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(NotFoundCardException::new);

        if (!card.getUser().getId().equals(user.getId())) {
            throw new NotMatchUserException();
        }

        return card;
    }
}
