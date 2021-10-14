package com.welog.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.welog.www.model.User;
import com.welog.www.repository.LikeItRepository;
import com.welog.www.repository.UserRepository;
import com.welog.www.service.UserService;

@Controller
@RequestMapping("/LikeIt")
public class LikeItController {

	@Autowired
	private LikeItRepository likeItRepository;

	@Autowired
	private UserRepository userRepository;


	@GetMapping("/add")
	public String add(Authentication authentication, @RequestParam(required = false) Long articleId,
			HttpServletRequest request) {

		String referer         = request.getHeader("Referer");
		String currentUsername = authentication.getName();
		User   user            = userRepository.findByUsername(currentUsername);
//		User user = userService.getUserFindByCurrentUsername(authentication.getName());

		likeItRepository.likeItAdd(articleId, user.getId());

		return "redirect:" + referer;
	}

	@GetMapping("/remove")
	public String remove(Authentication authentication, @RequestParam(required = false) Long articleId,
			HttpServletRequest request) {

		String referer         = request.getHeader("Referer");
		String currentUsername = authentication.getName();
		User   user            = userRepository.findByUsername(currentUsername);
//		User user = userService.getUserFindByCurrentUsername(authentication.getName());

		likeItRepository.likeItRemove(articleId, user.getId());

		return "redirect:" + referer;
	}
	
	@GetMapping("/deleteAllByUser")
	public String deleteAllByUser() {

		return "";
	}
	
// EOD
}

