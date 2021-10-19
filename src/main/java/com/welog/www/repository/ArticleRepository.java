package com.welog.www.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.welog.www.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	Page<Article> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);
//	List<Article> findBySubjectContainingOrContentContaining(String subject, String content);
	List<Article> findBySubjectContainingOrContentContainingOrderByCreatedDateDesc(String subject, String content);
//	List<Article> findByUser_id(Long id);
	List<Article> findByUser_idOrderByCreatedDateDesc(Long id);
	
	List<Article> findTop4ByOrderByLikehitDesc();
	
	long countByUser_id(Long user_id);

	@Transactional
	long deleteByUser_id(Long user_id);



//	@Query(value = "SELECT a.id, a.content, a.created_date, a.image_src, a.subject, a.updated_date, a.user_id "
//			+ "FROM article a LEFT OUTER JOIN user u on a.user_id=u.id "
//			+ "WHERE a.user_id = 10 "
//			+ "AND (a.content LIKE '%남겨%' OR "
//			+ "a.subject LIKE '%%')", nativeQuery = true)
//	List<Article> findByUser_idAndSearchForSubjectOrContent(Long id, String subject, String content);

}
