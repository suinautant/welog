package com.welog.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welog.www.model.Comment;
import com.welog.www.model.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByUserOrderByCreatedDateDesc(User user);

}
