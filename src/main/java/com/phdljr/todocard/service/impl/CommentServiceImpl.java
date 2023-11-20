package com.phdljr.todocard.service.impl;

import com.phdljr.todocard.dto.request.CommentRequestDto;
import com.phdljr.todocard.dto.response.CommentResponseDto;
import com.phdljr.todocard.entity.Card;
import com.phdljr.todocard.entity.Comment;
import com.phdljr.todocard.entity.User;
import com.phdljr.todocard.exception.type.NotFoundCardException;
import com.phdljr.todocard.exception.type.NotFoundCommentException;
import com.phdljr.todocard.exception.type.NotMatchUserException;
import com.phdljr.todocard.repository.CardRepository;
import com.phdljr.todocard.repository.CommentRepository;
import com.phdljr.todocard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;

    @Override
    public CommentResponseDto createComment(final Long cardId,
        final CommentRequestDto commentRequestDto, final User user) {

        Card card = cardRepository.findById(cardId).orElseThrow(NotFoundCardException::new);

        Comment comment = Comment.builder()
            .user(user)
            .content(commentRequestDto.getContent())
            .card(card)
            .build();
        comment = commentRepository.save(comment);
        return comment.toDto();
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(final Long commentId,
        final CommentRequestDto commentRequestDto, final User user) {
        Comment comment = findByCommentWithUser(commentId, user);

        comment.update(commentRequestDto.getContent());

        return comment.toDto();
    }

    @Override
    public Long deleteComment(final Long commentId, final User user) {
        Comment comment = findByCommentWithUser(commentId, user);

        commentRepository.delete(comment);

        return commentId;
    }

    private Comment findByCommentWithUser(final Long commentId, final User user) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(NotFoundCommentException::new);

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new NotMatchUserException();
        }

        return comment;
    }
}
