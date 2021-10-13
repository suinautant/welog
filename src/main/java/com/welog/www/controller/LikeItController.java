package com.welog.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/LikeIt")
public class LikeItController {

	@GetMapping("/OK")
	public String ok(HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		


		return "redirect:" + referer;

	}

}
