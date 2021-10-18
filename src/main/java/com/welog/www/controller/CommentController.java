package com.welog.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.welog.www.model.Article;
import com.welog.www.model.Comment;
import com.welog.www.model.User;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.CommentRepository;
import com.welog.www.repository.UserRepository;
import com.welog.www.service.CommentService;
import com.welog.www.validator.ArticleValidator;
import com.welog.www.validator.CommentValidator;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentValidator commentValidator;

	@PostMapping("/write")
	public String write(@Valid Comment comment, @RequestParam(required = false) Long articleId,
			@RequestParam(required = false) Long userId, BindingResult bindingResult,
			HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		commentValidator.validate(comment, bindingResult);
		if (bindingResult.hasErrors())
			return "redirect:" + referer;
		
		commentService.save(comment, articleId, userId);

		return "redirect:" + referer;

	}

}
