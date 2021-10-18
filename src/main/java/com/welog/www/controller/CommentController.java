package com.welog.www.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.welog.www.model.Article;
import com.welog.www.model.Comment;
import com.welog.www.repository.ArticleRepository;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private ArticleRepository articleRepository;

	@PostMapping("/write")
	public String write(Comment comment, @RequestParam(required = false) Long article_id) {

		Article article = articleRepository.findById(article_id).orElse(null);

		System.out.println("$$$$$$$$$$ args article id : " + article_id);
		System.out.println("$$$$$$$$$$ args article : " + article);
		System.out.println("$$$$$$$$$$ args username : " + comment.getUsername());

		return "";
	}

}
