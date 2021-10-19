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

import com.welog.www.classObject.LikeIt;
import com.welog.www.model.Article;
import com.welog.www.model.Comment;
import com.welog.www.model.User;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.UserRepository;
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
	public String main(Model model, Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {
		// 페이징 기능 삭제 - 메인용
		//		List<Article> articles = articleRepository.findBySubjectContainingOrContentContaining(searchText, searchText);
		List<Article> articles = articleRepository
				.findBySubjectContainingOrContentContainingOrderByCreatedDateDesc(searchText, searchText);

		// 좋아요 랭킹 상위 4개
		List<Article> likeArticles = articleRepository.findTop4ByOrderByLikehitDesc();

		model.addAttribute("likeArticles", likeArticles);
		model.addAttribute("articles", articles);
		return "article/main";
	}

	@GetMapping("list")
	public String list(Model model, @PageableDefault(size = 10) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {
		// id로 역순 정렬 모두 조회
		//		Page<Article> articles = articleRepository.findAll(pageable);
		Page<Article> articles = articleRepository.findBySubjectContainingOrContentContaining(searchText, searchText,
				pageable);
		// 아래 2줄 적용시 해당 페이지(10개 사이즈) 0번의 레코드만 가져옴
		//		PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
		//		Page<Article> articles = articleRepository.findAll(pageRequest);

		// 페이지 칼럼 개수 : 4개 이상 이동시 컬럼 자동 추가
		int columnPage = 4;
		int startPage  = Math.max(1, articles.getPageable().getPageNumber() - columnPage);
		int endPage    = Math.min(articles.getTotalPages(), articles.getPageable().getPageNumber() + columnPage);

		model.addAttribute("articles", articles);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "article/list";
	}

	@GetMapping("view")
	public String view(Model model, @RequestParam(required = false) Long id,
			@RequestParam(required = false) Long commentId, @RequestParam(required = false) String commentMode,
			Authentication authentication) {

		if (id != null) {
			Article article = articleRepository.findById(id).orElse(null);
			// id 값이 Long 타입이 아니거나
			// 없는 게시물에 접근 시 목록으로 보내기
			if (article == null) {
				return "redirect:/article/list";
			}
			model.addAttribute("article", article);

			// 댓글
			List<Comment> comments = article.getComments();
			model.addAttribute("comments", comments);

			// 좋아요 누른 사용자인지 확인  (true : 좋아요 누른 사용자)
			LikeIt likeIt = new LikeIt();
			if (authentication != null) {
				likeIt.setLikeUser(likeItService.isLikeUser(article, authentication));
			}
			likeIt.setCountLikeUser(likeItService.countLikeUser(article));
			model.addAttribute("likeIt", likeIt);

			// 로그인시 유저 정보
			if (authentication != null) {
				String currentUsername = authentication.getName();
				User   user            = userRepository.findByUsername(currentUsername);
				model.addAttribute("user", user);
			}

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
		}

		return "article/view";
	}

	@GetMapping("form")
	public String form(Model model, @RequestParam(required = false) Long id) {

		if (id == null) {
			Article article = new Article();
			article.setLikehit(0l);
			model.addAttribute("article", article);
			//			model.addAttribute("article", new Article());
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
	public String formPost(@Valid Article article, @RequestParam(required = false) Long id, BindingResult bindingResult,
			Authentication authentication) {

		// validator 검증 
		// ArticleValidator 확인 후 오류 있을 시 /article/form 리턴
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
		return "redirect:/";
	}

	@GetMapping("delete")
	public String delete(@RequestParam(required = true) Long id, Authentication authentication) {
		Article article         = articleRepository.findById(id).orElse(null);
		String  articleUsername = article.getUser().getUsername();
		String  currentUsername = authentication.getName();

		// 사용자 인증 : : 원글 사용자면 삭제
		if (currentUsername.equals(articleUsername))
			articleRepository.deleteById(id);

		return "redirect:/";
	}

	@GetMapping("/deleteAllArticleByUser")
	public String deleteAllArticleByUser(Authentication authentication) {

		String currentUsername = authentication.getName();
		long   userId          = userService.getUserIdFindByUsername(currentUsername);
		articleRepository.deleteByUser_id(userId);

		return "redirect:/my/info";
	}

}
