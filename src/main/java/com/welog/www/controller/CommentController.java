package com.welog.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.welog.www.model.Article;
import com.welog.www.model.Comment;
import com.welog.www.repository.CommentRepository;
import com.welog.www.service.CommentService;
import com.welog.www.service.UserService;
import com.welog.www.validator.CommentValidator;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentValidator commentValidator;

	@Autowired
	private UserService userService;

	@GetMapping("/delete")
	public String delete(@RequestParam(required = false) Long commentId, @RequestParam(required = false) Long userId,
			Authentication authentication, HttpServletRequest request) {

		String referer         = request.getHeader("Referer");
		String currentUsername = authentication.getName();

		commentService.delete(commentId, userId, currentUsername);

		return "redirect:" + referer;
	}

	@GetMapping("deleteAllCommentByUser")
	public String deleteAllCommentByUser(Model model, Authentication authentication, HttpServletRequest request) {

		String referer = request.getHeader("Referer");

		long userId = userService.findUserIdByCurrentUsername(authentication);
		
//		return "redirect:" + referer;
		return "";
	}

	@PostMapping("/edit")
	public String edit(Model model, @Valid Comment editComment, @RequestParam(required = false) Long articleId,
			@RequestParam(required = false) Long userId, BindingResult bindingResult, HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		commentValidator.validate(editComment, bindingResult);
		if (bindingResult.hasErrors())
			return "redirect:" + referer;

		commentService.save(editComment, articleId, userId);

		model.addAttribute("id", articleId);

		return "redirect:/article/view?id=" + articleId;
	}

	@PostMapping("/write")
	public String write(@Valid Comment comment, @RequestParam(required = false) Long articleId,
			@RequestParam(required = false) Long userId,
			@RequestParam(required = true, defaultValue = "write") String commentMode, BindingResult bindingResult,
			HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		commentValidator.validate(comment, bindingResult);
		if (bindingResult.hasErrors())
			return "redirect:" + referer;

		commentService.save(comment, articleId, userId);

		return "redirect:" + referer;
	}

}
