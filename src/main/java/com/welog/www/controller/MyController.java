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
import com.welog.www.model.Comment;
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
		List<Article> articles = likeItRepository.findByLikeItUserOrderByCreatedDateDesc(userId);
		model.addAttribute("articles", articles);

		return "my/like";
	}
	
	@GetMapping("comment")
	public String comment(Model model, Authentication authentication) {

		String currentUsername = authentication.getName();
		long   userId          = userService.getUserIdFindByUsername(currentUsername);
		List<Article> articles = likeItRepository.findByLikeItUserOrderByCreatedDateDesc(userId);
		model.addAttribute("articles", articles);
		
		return "my/comment";
	}
	
	@GetMapping("deleteAllCommentByUser")
	public String deleteAllCommentByUser(Model model, Authentication authentication) {
		
		return "my/deleteAllCommentByUser";
	}
	

	// EOD
}
