package com.welog.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welog.www.model.Article;
import com.welog.www.model.Comment;
import com.welog.www.model.User;
import com.welog.www.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public void save(Comment comment, Long articleId, Long userId) {
		Article article = new Article();
		article.setId(articleId);
		User user = new User();
		user.setId(userId);

		comment.setArticle(article);
		comment.setUser(user);

		commentRepository.save(comment);
	}

}
