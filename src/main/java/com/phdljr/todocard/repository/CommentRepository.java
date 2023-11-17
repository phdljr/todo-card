package com.phdljr.todocard.repository;

import com.phdljr.todocard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
