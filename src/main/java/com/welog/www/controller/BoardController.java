package com.welog.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welog.www.model.Board;
import com.welog.www.repository.BoardRepository;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("list")
	public String list(Model model) {
		List<Board> boards = boardRepository.findAll();
		
		return "board/list";
	}

}
