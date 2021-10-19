package com.welog.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welog.www.model.Article;
import com.welog.www.model.Comment;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.CommentRepository;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping("/")
	public String testMain() {

//		댓글 테스트
		Long          articleId = 91l;
		Article       article   = articleRepository.findById(articleId).orElse(null);
		List<Comment> comments  = article.getComments();

		System.out.println("$$$$$$$$$$$$$ article id 92's comments : " + comments);
		for (Comment comment : comments) {
			System.out.println("$$$$$$$$$$$$$ comment : " + comment.getId());
			System.out.println("$$$$$$$$$$$$$ comment : " + comment.getUser().getUsername());
			System.out.println("$$$$$$$$$$$$$ comment : " + comment.getContent());
		}

		return "";
	}

}
