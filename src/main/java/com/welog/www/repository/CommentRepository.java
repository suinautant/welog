package com.welog.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welog.www.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
