package com.welog.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welog.www.model.Article;
import com.welog.www.repository.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("list")
	public String list(Model model) {
		// id로 역순 정렬 모두 조회
		List<Article> articles = articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		
		// TEST
		System.out.println(articles);

		model.addAttribute("articles", articles);
		
		return "article/list";
	}

}
