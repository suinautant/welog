package com.welog.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welog.www.model.Article;
import com.welog.www.model.User;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.UserRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Article save(String username, Article article) {
		User uesr = userRepository.findByUsername(username);
		article.setUser(uesr);
		
		return articleRepository.save(article);
		
	}
	
}
