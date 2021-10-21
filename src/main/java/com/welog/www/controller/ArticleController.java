package com.welog.www.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.welog.www.classObject.LikeIt;
import com.welog.www.model.Article;
import com.welog.www.model.Comment;
import com.welog.www.model.User;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.UserRepository;
import com.welog.www.service.ArticlePictureService;
import com.welog.www.service.ArticleService;
import com.welog.www.service.CommentService;
import com.welog.www.service.LikeItService;
import com.welog.www.service.UserService;
import com.welog.www.validator.ArticleValidator;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticlePictureService articlePictureService;

	@Autowired
	private ArticleValidator articleValidator;

	@Autowired
	private CommentService commentService;

	@Autowired
	private LikeItService likeItService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String main(
			Model model,
			Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {

		List<Article> articles = articleRepository
				.findBySubjectContainingOrContentContainingOrderByCreatedDateDesc(searchText, searchText);

		// 좋아요 랭킹 상위 4개
		List<Article> likeArticles = articleRepository.findTop4ByOrderByLikehitDesc();

		String defaultMainImg= "/image/welog_main_thumbnail.png";
		model.addAttribute("defaultMainImg", defaultMainImg);
		model.addAttribute("likeArticles", likeArticles);
		model.addAttribute("articles", articles);

		return "article/main";
	}

	@GetMapping("list")
	public String list(
			Model model,
			@PageableDefault(size = 10) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {

		Page<Article> articles = articleRepository.findBySubjectContainingOrContentContaining(searchText, searchText,
				pageable);

		// 페이지 칼럼 개수 : 4개 이상 이동시 컬럼 자동 추가
		int columnPage = 4;
		int startPage = Math.max(1, articles.getPageable().getPageNumber() - columnPage);
		int endPage = Math.min(articles.getTotalPages(), articles.getPageable().getPageNumber() + columnPage);

		model.addAttribute("articles", articles);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "article/list";
	}

	@GetMapping("view")
	public String view(
			Model model,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long commentId,
			@RequestParam(required = false) String commentMode,
			Authentication authentication) {

		if (id == null)
			return "redirect:/";
		Article article = articleRepository.findById(id).orElse(null);

		// 없는 게시물에 접근 시 메인으로 보내기
		if (article == null) {
			return "redirect:/";
		}

		// Article 객체 - 게시물
		model.addAttribute("article", article);

		// LikeIt 객체 - 좋아요 누른 사용자인지 확인  (true : 좋아요 누른 사용자)
		LikeIt likeIt = new LikeIt();
		if (authentication != null) {
			likeIt.setLikeUser(likeItService.isLikeUser(article, authentication));
		}
		likeIt.setCountLikeUser(likeItService.countLikeUser(article));
		model.addAttribute("likeIt", likeIt);

		// User 객체 - 로그인 유저 정보
		if (authentication != null) {
			String currentUsername = authentication.getName();
			User user = userRepository.findByUsername(currentUsername);
			model.addAttribute("user", user);
		}

		// Comment 객체 -  댓글
		List<Comment> comments = article.getComments();
		model.addAttribute("comments", comments);

		// 댓글 모드(작성, 수정)에 따른 객체
		Comment comment = new Comment();
		model.addAttribute("comment", comment);

		if (commentMode == null) {
			commentMode = "write";
			model.addAttribute("commentMode", commentMode);
		} else if (commentMode.equals("edit")) {
			Comment editComment = commentService.findById(commentId);
			model.addAttribute("editComment", editComment);
			model.addAttribute("commentMode", commentMode);
		}
		
		return "article/view";
	}

	@GetMapping("form")
	public String form(
			Model model,
			@RequestParam(required = false) Long id) {

		if (id == null) {
			Article article = new Article();
			article.setLikehit(0l);
			model.addAttribute("article", article);

		} else {
			Article article = articleRepository.findById(id).orElse(null);

			// 예외처리: id 값이 Long 타입이 아니거나 없는 게시물에 접근 시 목록으로 보내기
			if (article == null) {
				return "redirect:/article/list";
			}

			// 현재 시간 updated_date에 설정
			article.setUpdatedDate(LocalDateTime.now());
			model.addAttribute("article", article);
		}
		return "article/form";
	}

	@PostMapping("form")
	public String formPost(
			@Valid Article article,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false, name = "files") List<MultipartFile> multipartFiles,
			BindingResult bindingResult,
			Authentication authentication)
			throws Exception {

		// validator  
		articleValidator.validate(article, bindingResult);
		if (bindingResult.hasErrors())
			return "/article/form";

		// 수정
		if (id != null) {
			String articleUsername = article.getUser().getUsername();
			String currentUsername = authentication.getName();

			// 사용자 인증 : 원글 사용자 아니면 리다이렉트
			if (!currentUsername.equals(articleUsername))
				return "redirect:/";
		}

		String username = authentication.getName();
		articleService.save(username, article);
		articlePictureService.save(article, multipartFiles);

		return "redirect:/";
	}

	@GetMapping("delete")
	public String delete(
			@RequestParam(required = true) Long id,
			Authentication authentication) {

		Article article = articleRepository.findById(id).orElse(null);
		String articleUsername = article.getUser().getUsername();
		String currentUsername = authentication.getName();

		// 사용자 인증. 원글 사용자면 삭제
		if (currentUsername.equals(articleUsername))
			articleService.deleteByArticle(article);

		return "redirect:/";
	}

	@GetMapping("/deleteAllArticleByUser")
	public String deleteAllArticleByUser(
			Authentication authentication) {

		String currentUsername = authentication.getName();
		long userId = userService.getUserIdFindByUsername(currentUsername);
		articleService.deleteByUserId(userId);

		return "redirect:/my/info";
	}

}
