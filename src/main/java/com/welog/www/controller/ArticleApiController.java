package com.welog.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welog.www.repository.ArticleRepository;

@RestController
@RequestMapping("/api")
public class ArticleApiController {
	
	@Autowired
	private ArticleRepository articleRepository;

//	@Secured("ROLE_ADMIN")
	@DeleteMapping("/article/{id}")
	public void delete(@PathVariable Long id) {
		articleRepository.deleteById(id);
	}
}
