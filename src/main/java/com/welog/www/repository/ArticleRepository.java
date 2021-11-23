package com.welog.www.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.welog.www.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	List<Article> findBySubject(String subject);

	Page<Article> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);

	List<Article> findBySubjectContainingOrContentContainingOrderByCreatedDateDesc(String subject, String content);

	List<Article> findByUser_idOrderByCreatedDateDesc(Long id);

	List<Article> findTop4ByOrderByLikehitDesc();

	long countByUser_id(Long user_id);

	@Transactional
	long deleteByUser_id(Long user_id);

}
