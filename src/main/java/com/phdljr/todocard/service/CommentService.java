package com.phdljr.todocard.service;

import com.phdljr.todocard.dto.request.CommentRequestDto;
import com.phdljr.todocard.dto.response.CommentResponseDto;
import com.phdljr.todocard.entity.User;

public interface CommentService {

    CommentResponseDto createComment(Long cardId, CommentRequestDto commentRequestDto, User user);

    CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto,
        User user);

    Long deleteComment(Long commentId, User user);
}
