package com.welog.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welog.www.model.User;
import com.welog.www.service.UserService;

@Controller
@RequestMapping("/account")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("login")
	public String login() {
		return "account/login";
	}

	@GetMapping("register")
	public String register(Model model) {
		return "account/register";
	}
	
	@PostMapping("register")
	public String registerPost(User user) {
		userService.save(user);
		return "redirect:/";
	}

}
