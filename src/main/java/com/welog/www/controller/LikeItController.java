package com.welog.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.welog.www.model.Article;
import com.welog.www.model.User;
import com.welog.www.service.ArticleService;
import com.welog.www.service.LikeItService;
import com.welog.www.service.UserService;

@Controller
@RequestMapping("/LikeIt")
public class LikeItController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private LikeItService likeItService;

	@Autowired
	private UserService userService;

	@GetMapping("/add")
	public String add(Authentication authentication, @RequestParam(required = false) Long articleId,
			HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		String currentUsername = authentication.getName();
		User user = userService.findByUsername(currentUsername);

		Article article = articleService.findById(articleId);

		likeItService.likeItAdd(articleId, user.getId());
		likeItService.likeItCount(articleId, article.getLikehit() + 1);

		return "redirect:" + referer;
	}

	@GetMapping("/remove")
	public String remove(Authentication authentication, @RequestParam(required = false) Long articleId,
			HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		String currentUsername = authentication.getName();
		User user = userService.findByUsername(currentUsername);

		Article article = articleService.findById(articleId);

		likeItService.likeItRemove(articleId, user.getId());
		likeItService.likeItCount(articleId, article.getLikehit() + -1);

		return "redirect:" + referer;
	}

	@GetMapping("/deleteAllByUser")
	public String deleteAllByUser() {

		return "";
	}

	// EOD
}
