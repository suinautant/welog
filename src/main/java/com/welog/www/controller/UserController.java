package com.welog.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welog.www.model.User;
import com.welog.www.repository.UserRepository;

@Controller
@RequestMapping("/account")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("register")
	public String register() {
		return "account/register";
	}
	
	@PostMapping("register")
	public String registerPost(User user) {
		user.setEnabled(true);
		userRepository.save(user);
		return "redirect:/";
	}
	
	
	@GetMapping("login")
	public String login() {
		return "account/login";
	}

}
