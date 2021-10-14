package com.welog.www.controller;

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

import com.welog.www.model.User;
import com.welog.www.repository.UserRepository;
import com.welog.www.service.UserService;
import com.welog.www.validator.UserValidator;

@Controller
@RequestMapping("/account")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserRepository userRepository;
	

	@GetMapping("login")
	public String login() {
		return "account/login";
	}

	@GetMapping("register")
	public String register(Model model, 
			@RequestParam(required = false) Long id) {
		if (id == null) {
			model.addAttribute("user", new User());
		}

		return "account/register";
	}
	
	@PostMapping("register")
	public String registerPost(@Valid User user,
			BindingResult bindingResult) {
		// validator 검증
		// UserValidator 확인 후 오류 있을 시 /account/register 리턴
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors())
			return "/account/register";

		userService.save(user);
		return "redirect:/";
	}
	
	@PostMapping("/inactiveUser")
	public String inactiveUser(@Valid User user, Authentication authentication, HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		String currentUsername = authentication.getName();
		// 접속자와 요청자가 동일 여부
		if (currentUsername.equals(user.getUsername())) {
			// getEnable toggle
			if (user.getEnabled()) {
				userRepository.updateEnabled(user.getId(), false);
			} else  {
				userRepository.updateEnabled(user.getId(), true);
			}
		}
			
		return "redirect:" + referer;
	}
	
	@PostMapping("/leaveUser")
	public String leaveUser(@Valid User user, Authentication authentication) {

		String currentUsername = authentication.getName();
		// 접속자와 요청자가 동일 여부
		if (currentUsername.equals(user.getUsername())) {
			userRepository.deleteById(user.getId());
		}
			
//		return "redirect:/logout";
		return "redirect:/";
	}

// EOD
}
