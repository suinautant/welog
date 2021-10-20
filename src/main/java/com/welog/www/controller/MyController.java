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
import com.welog.www.model.Comment;
import com.welog.www.model.User;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.LikeItRepository;
import com.welog.www.service.CommentService;
import com.welog.www.service.UserService;

@Controller
@RequestMapping("/my")
public class MyController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private LikeItRepository likeItRepository;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String my(Model model, Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText, Authentication authentication) {

		long userId = userService.findUserIdByCurrentUsername(authentication);

		List<Article> articles = articleRepository.findByUser_idOrderByCreatedDateDesc(userId);

		model.addAttribute("articles", articles);

		return "my/main";
	}

	@GetMapping("comment")
	public String comment(Model model, Authentication authentication) {

		Long userId = userService.findUserIdByCurrentUsername(authentication);

		List<Comment> comments = commentService.findByUserIdOrderByCreatedDateDesc(userId);

		model.addAttribute("comments",comments);

		return "my/comment";
	}

	@GetMapping("info")
	public String info(Model model, Authentication authentication) {

		long userId = userService.findUserIdByCurrentUsername(authentication);
		User user   = userService.findById(userId);

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

		long          userId   = userService.findUserIdByCurrentUsername(authentication);
		List<Article> articles = likeItRepository.findByLikeItUserOrderByCreatedDateDesc(userId);
		model.addAttribute("articles", articles);

		return "my/like";
	}

	// EOD
}
