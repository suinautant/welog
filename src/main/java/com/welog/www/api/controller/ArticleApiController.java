package com.welog.www.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.welog.www.model.Article;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.service.ArticleService;

@RestController
class ArticleApiController {

	private final ArticleService articleService;

	private final ArticleRepository articleRepository;

	public ArticleApiController(ArticleService articleService, ArticleRepository articleRepository) {
		this.articleService = articleService;
		this.articleRepository = articleRepository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/articles")
	List<Article> all() {
		return articleService.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/articles")
	Article newArticle(@RequestBody Article newArticle) {
		return articleService.save(newArticle);
	}

	// Single item

	@GetMapping("/articles/{id}")
	Article one(@PathVariable Long id) {

		// 오류 발생으로 주석, 동작 확인 후 수정 예정
//    return articleService.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
		return articleService.findById(id);
	}

	@PutMapping("/articles/{id}")
	Article replaceArticle(@RequestBody Article newArticle, @PathVariable Long id) {

		// 오류 발생으로 주석, 동작 확인 후 수정 예정
//    return articleService.findById(id)
		return articleRepository.findById(id).map(article -> {
			article.setSubject(newArticle.getSubject());
			article.setContent(newArticle.getContent());
			return articleService.save(article);
		}).orElseGet(() -> {
			newArticle.setId(id);
			return articleService.save(newArticle);
		});
	}

	@DeleteMapping("/articles/{id}")
	void deleteArticle(@PathVariable Long id) {
		articleService.deleteById(id);
	}
}
