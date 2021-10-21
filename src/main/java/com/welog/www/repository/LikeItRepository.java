package com.welog.www.repository;

import java.util.List;

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
	@Query(value = "UPDATE article"
			+ " SET likehit = ?2"
			+ " WHERE id = ?1",
			nativeQuery = true)
	void likeItAddCount(Long articleId, Long likehit);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM like_article_user" 
			+ " WHERE article_id = ?1 AND user_id = ?2", 
			nativeQuery = true)
	void likeItRemove(Long articleId, Long userId);

	@Query(value = "SELECT COUNT(*)"
			+ " FROM like_article_user"
			+ " WHERE user_id=?1", 
			nativeQuery = true)
	int countLikeItByUser(Long userId);
	
//	SELECT a.id, a.user_id, a.subject, a.content, a.image_src, a.created_date, a.updated_date
//	FROM like_article_user l
//	LEFT JOIN article a
//	ON l.article_id =a.id
//	WHERE l.user_id = 10;
//			+ " a.id, a.user_id, a.subject, a.content, a.image_src, a.likehit,"
	@Query(value = "SELECT"
			+ " a.id, a.user_id, a.subject, a.content, a.likehit, a.created_date, a.updated_date"
			+ " FROM like_article_user l"
			+ " LEFT JOIN article a"
			+ " ON l.article_id =a.id"
			+ " WHERE l.user_id = ?1"
			+ " ORDER BY created_date DESC",
			nativeQuery = true)
	List<Article> findByLikeItUserOrderByCreatedDateDesc(Long userId);

}
