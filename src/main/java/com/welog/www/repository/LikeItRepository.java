package com.welog.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.welog.www.model.Article;

public interface LikeItRepository extends JpaRepository<Article, Long> {
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO like_article_user "
			+ " (article_id, user_id)"
			+ " VALUES (?1, ?2)",
			nativeQuery = true)
	void likeItAdd(Long articleId, Long userId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM like_article_user"
			+ " WHERE article_id = ?1 AND user_id = ?2",
			nativeQuery = true)
	void likeItRemove(Long articleId, Long userId);

}
