package com.phdljr.todocard.controller;

import com.phdljr.todocard.dto.request.CardRequestDto;
import com.phdljr.todocard.dto.response.CardResponseDto;
import com.phdljr.todocard.dto.response.CardsResponseDto;
import com.phdljr.todocard.security.userdetails.UserDetailsImpl;
import com.phdljr.todocard.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/cards/{cardId}")
    public ResponseEntity<CardResponseDto> getCard(@PathVariable Long cardId) {
        CardResponseDto cardResponseDto = cardService.getCard(cardId);
        return ResponseEntity.ok(cardResponseDto);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardsResponseDto>> getCards(
        @RequestParam(required = false, name = "title", defaultValue = "") String title) {
        List<CardsResponseDto> cardsResponseDto = cardService.getCards(title);
        return ResponseEntity.ok(cardsResponseDto);
    }

    @PostMapping("/cards")
    public ResponseEntity<CardResponseDto> createCard(
        @RequestBody CardRequestDto cardRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto cardResponseDto = cardService.createCard(cardRequestDto,
            userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(cardResponseDto);
    }

    @PutMapping("/cards/{cardId}")
    public ResponseEntity<CardResponseDto> updateCard(
        @PathVariable Long cardId,
        @RequestBody CardRequestDto cardRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto cardResponseDto = cardService.updateCard(cardId, cardRequestDto,
            userDetails.getUser());
        return ResponseEntity.ok(cardResponseDto);
    }

    @PutMapping("/cards/{cardId}/hide")
    public ResponseEntity<CardResponseDto> toggleHideCard(@PathVariable Long cardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto cardResponseDto = cardService.toggleHideCard(cardId,
            userDetails.getUser());
        return ResponseEntity.ok(cardResponseDto);
    }

    @PutMapping("/cards/{cardId}/finish")
    public ResponseEntity<CardResponseDto> toggleFinishCard(@PathVariable Long cardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto cardResponseDto = cardService.toggleFinishCard(cardId,
            userDetails.getUser());
        return ResponseEntity.ok(cardResponseDto);
    }

    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<Long> deleteCard(@PathVariable Long cardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long id = cardService.deleteCard(cardId, userDetails.getUser());
        return ResponseEntity.ok(id);
    }
}
