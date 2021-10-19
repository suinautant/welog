package com.welog.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welog.www.model.Article;
import com.welog.www.model.Comment;
import com.welog.www.model.User;
import com.welog.www.repository.CommentRepository;
import com.welog.www.repository.UserRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Comment findById(Long id) {
		return commentRepository.findById(id).orElse(null);
	}
	
	public void save(Comment comment, Long articleId, Long userId) {
		Article article = new Article();
		article.setId(articleId);
		User user = new User();
		user.setId(userId);

		comment.setArticle(article);
		comment.setUser(user);

		commentRepository.save(comment);
	}
	
	public void delete(Long commentId, Long userId, String username) {
		User user = userRepository.findById(userId).orElse(null);
		if (user.getUsername().equals(username)) {
			commentRepository.deleteById(commentId);
		}
	}
	
	public boolean isOwnerCurrentUser(Long commentId, String currentUser) {
		Comment comment = commentRepository.findById(commentId).orElse(null);
		User user = userRepository.findByUsername(currentUser);
		
		if (comment.getUser().getId() == user.getId()) {
			return true;
		}
		
		return false;
	}

}
