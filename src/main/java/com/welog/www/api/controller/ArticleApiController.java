package com.welog.www.api.controller;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.welog.www.model.Article;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.service.ArticleService;

@RestController
@RequestMapping("/api")
class ArticleApiController {

	private final ArticleService articleService;

	// map() 오류로 인해 Repository 사용
	private final ArticleRepository articleRepository;

	ArticleApiController(ArticleService articleService, ArticleRepository articleRepository) {
		this.articleService = articleService;
		this.articleRepository = articleRepository;
	}

	@SuppressWarnings("deprecation")
	@GetMapping("/articles")
	List<Article> all(@RequestParam(required = false) String subject) {
		if(StringUtils.isEmpty(subject)) {
					return articleService.findAll();
		} else {
			return articleService.findBySubject(subject);
		}
	}

	@PostMapping("/articles")
	Article newArticle(@RequestBody Article newArticle) {
		return articleService.save(newArticle);
	}

	// Single item
	@GetMapping("/articles/{id}")
	Article one(@PathVariable Long id) {

//    return articleService.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
		return articleService.findById(id);
	}

	@PutMapping("/articles/{id}")
	Article replaceArticle(@RequestBody Article newArticle, @PathVariable Long id) {

//    return articleService.findById(id)
		return articleRepository.findById(id)
				.map(article -> {
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
