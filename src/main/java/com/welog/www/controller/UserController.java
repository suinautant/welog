package com.welog.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welog.www.repository.UserRepository;

@Controller
@RequestMapping("/account")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	

}
