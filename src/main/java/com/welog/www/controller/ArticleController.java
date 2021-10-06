package com.welog.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order.Direction;
import com.welog.www.model.Article;
import com.welog.www.repository.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping("list")
	public String list(Model model,
			@PageableDefault(size = 10) Pageable pageable) {
		// id로 역순 정렬 모두 조회
		Page<Article> articles = articleRepository.findAll(pageable);
		// 아래 2줄 적용시 해당 페이지(10개 사이즈) 0번의 레코드만 가져옴
//		PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
//		Page<Article> articles = articleRepository.findAll(pageRequest);
		
		int startPage = Math.max(1, articles.getPageable().getPageNumber() - 4);
		int endPage   = Math.min(articles.getTotalPages(), articles.getPageable().getPageNumber() + 4);

		model.addAttribute("articles", articles);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "article/list";
	}

	@GetMapping("view")
	public String view(Model model, @RequestParam(required = false) Long id) {

		if (id != null) {
			Article article = articleRepository.findById(id).orElse(null);
			// id 값이 Long 타입이 아니거나
			// 없는 게시물에 접근 시 목록으로 보내기
			if (article == null) {
				return "redirect:/article/list";
			}
			model.addAttribute(article);
		}

		return "article/view";

	}

	@GetMapping("form")
	public String form(Model model, @RequestParam(required = false) Long id) {
		if (id != null) {
			Article article = articleRepository.findById(id).orElse(null);
			// id 값이 Long 타입이 아니거나
			// 없는 게시물에 접근 시 목록으로 보내기
			if (article == null) {
				return "redirect:/article/list";
			}
			model.addAttribute(article);
		}

		return "article/form";
	}

}
