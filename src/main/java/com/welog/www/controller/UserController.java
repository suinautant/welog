package com.welog.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welog.www.model.User;
import com.welog.www.service.UserService;
import com.welog.www.validator.UserValidator;

@Controller
@RequestMapping("/account")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping("login")
	public String login() {
		return "account/login";
	}

	@GetMapping("register")
	public String register(Model model) {
		return "account/register";
	}
	
	@PostMapping("register")
	public String registerPost(User user,
			BindingResult bindingResult) {
		// validator 검증
		// UserValidator 확인 후 오류 있을 시 /account/register 리턴
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors())
			return "/account/register";

		userService.save(user);
		return "redirect:/";
	}

}
