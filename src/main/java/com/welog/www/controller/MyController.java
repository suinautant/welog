package com.welog.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {
	
	@GetMapping("/")
	public String my() {
		
		return "my/main";
	}

}
