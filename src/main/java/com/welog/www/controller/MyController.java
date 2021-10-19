package com.welog.www.controller;

import java.util.Iterator;
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
import com.welog.www.model.User;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.LikeItRepository;
import com.welog.www.repository.UserRepository;
import com.welog.www.service.UserService;

@Controller
@RequestMapping("/my")
public class MyController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private LikeItRepository likeItRepository;

	@GetMapping("/")
	public String my(Model model, Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText, Authentication authentication) {

		String currentUsername = authentication.getName();
		long   userId          = userService.getUserIdFindByUsername(currentUsername);

		// 페이징 기능 삭제 - 메인용
		//		List<Article> articles = articleRepository.findBySubjectContainingOrContentContaining(searchText, searchText);
		// NATIVE SQL QUERY - content like or subject like 했을 때 하나의 조건 입력 값이 null 이면 검색식 무효화
		//		List<Article> articles = articleRepository.findByUser_idAndSearchForSubjectOrContent(userId, searchText, searchText);
//		List<Article> articles = articleRepository.findByUser_id(userId);
		List<Article> articles = articleRepository.findByUser_idOrderByCreatedDateDesc(userId);

		model.addAttribute("articles", articles);

		return "my/main";
	}

	@GetMapping("/info")
	public String info(Model model, Authentication authentication) {

		String currentUsername = authentication.getName();
		long   userId          = userService.getUserIdFindByUsername(currentUsername);
		User   user            = userRepository.findByUsername(currentUsername);

		// 작성 글 총합
		Long countArticleByUser = articleRepository.countByUser_id(userId);
		model.addAttribute("countArticleByUser", countArticleByUser);

		// 좋아요 총합
		int countLikeItByUser = likeItRepository.countLikeItByUser(userId);
		model.addAttribute("countLikeItByUser", countLikeItByUser);

		model.addAttribute("user", user);

		return "my/info";
	}

	@GetMapping("like")
	public String like(Model model, Authentication authentication) {

		String currentUsername = authentication.getName();
		long   userId          = userService.getUserIdFindByUsername(currentUsername);
//		List<Article> articles = likeItRepository.findByLikeItUser(userId);
		List<Article> articles = likeItRepository.findByLikeItUserOrderByCreatedDateDesc(userId);
		model.addAttribute("articles", articles);

		return "my/like";
	}

	// EOD
}
