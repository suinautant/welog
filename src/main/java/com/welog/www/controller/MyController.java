package com.welog.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.welog.www.model.Article;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.service.UserService;

@Controller
@RequestMapping("/my")
public class MyController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String my(Model model, Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText,
			Authentication authentication) {
		
		String currentUsername = authentication.getName();
		long userId = userService.getUserIdFindByUsername(currentUsername);
		
		// 페이징 기능 삭제 - 메인용
//		List<Article> articles = articleRepository.findBySubjectContainingOrContentContaining(searchText, searchText);
		// NATIVE SQL QUERY - content like or subject like 했을 때 하나의 조건 입력 값이 null 이면 검색식 무효화
//		List<Article> articles = articleRepository.findByUser_idAndSearchForSubjectOrContent(userId, searchText, searchText);
		List<Article> articles = articleRepository.findByUser_id(userId);

		model.addAttribute("articles", articles);

		return "my/main";
	}

}
