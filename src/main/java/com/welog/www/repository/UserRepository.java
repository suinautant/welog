package com.welog.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.welog.www.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE user"
			+ " SET enabled= ?2 "
			+ " WHERE id = ?1", nativeQuery = true)
	void updateEnabled(Long id, boolean Enabled);

//	@Query(value = "SELECT a.id, a.content, a.created_date, a.image_src, a.subject, a.updated_date, a.user_id "
//			+ "FROM article a LEFT OUTER JOIN user u on a.user_id=u.id "
//			+ "WHERE a.user_id = 10 "
//			+ "AND (a.content LIKE '%남겨%' OR "
//			+ "a.subject LIKE '%%')", nativeQuery = true)
//	List<Article> findByUser_idAndSearchForSubjectOrContent(Long id, String subject, String content);

}
