package com.phdljr.todocard.service;

import com.phdljr.todocard.dto.request.CardRequestDto;
import com.phdljr.todocard.dto.response.CardResponseDto;
import com.phdljr.todocard.dto.response.CardsResponseDto;
import com.phdljr.todocard.entity.User;
import java.util.List;

public interface CardService {

    CardResponseDto getCard(Long cardId);

    List<CardsResponseDto> getCards();

    CardResponseDto updateCard(Long cardId, CardRequestDto cardRequestDto, User user);

    CardResponseDto createCard(CardRequestDto cardRequestDto, User user);

    Long deleteCard(Long cardId, User user);

    CardResponseDto toggleHideCard(Long cardId, User user);

    CardResponseDto toggleFinishCard(Long cardId, User user);
}
