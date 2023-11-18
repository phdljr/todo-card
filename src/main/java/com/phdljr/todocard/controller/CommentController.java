package com.phdljr.todocard.controller;

import com.phdljr.todocard.dto.request.CommentRequestDto;
import com.phdljr.todocard.dto.response.CommentResponseDto;
import com.phdljr.todocard.security.userdetails.UserDetailsImpl;
import com.phdljr.todocard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{cardId}")
    public ResponseEntity<CommentResponseDto> createComment(
        @PathVariable Long cardId,
        @RequestBody CommentRequestDto commentRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto
            = commentService.createComment(cardId, commentRequestDto, userDetails.getUser());
        return ResponseEntity.ok(commentResponseDto);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
        @PathVariable Long commentId,
        @RequestBody CommentRequestDto commentRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto
            = commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
        return ResponseEntity.ok(commentResponseDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Long> deleteComment(
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long id = commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.ok(id);
    }
}
