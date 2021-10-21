package com.welog.www.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.welog.www.model.Article;
import com.welog.www.model.ArticlePicture;
import com.welog.www.model.User;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.UserRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	// user_id로 카운트
	public Long countByUser_id(Long userId) {
		return articleRepository.countByUser_id(userId);
	}

	// article로 삭제
	public void deleteByArticle(Article article) {

		List<String> filenames = transferFilenamepath(article);

		// 첨부 파일 삭제
		for (String filename : filenames) {
			deleteFileByDeleteArticle(filename);
		}

		// article 게시물 삭제
		articleRepository.deleteById(article.getId());
	}

	// user id로 article 모두 삭제
	public void deleteByUserId(Long userId) {

		// 사용자가 작성한 모든 게시물
		List<Article> articles = userService.getArticlesFindById(userId);

		// 모든 게시물 삭제
		for (Article article : articles) {
			deleteByArticle(article);
		}
	}

	// article 삭제 할 때 첨부 된 파일 삭제
	public void deleteFileByDeleteArticle(String filenamePath) {

		File deleteFile = new File(filenamePath);

		if (deleteFile.exists()) {
			deleteFile.delete();
		} else {
		}
	}

	// article id로 찾기
	public Article findById(Long id) {
		return articleRepository.findById(id).orElse(null);
	}
	
	// 제목과 내용 검색 결과를 작성자 역순 정렬 
	public List<Article> findBySubjectContainingOrContentContainingOrderByCreatedDateDesc(String subject, String content) {
		 return articleRepository.findBySubjectContainingOrContentContainingOrderByCreatedDateDesc(subject, content);
	}

	// 제목과 내용 검색 결과를 작성자 역순 정렬 
	public Page<Article> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable) {
		 return articleRepository.findBySubjectContainingOrContentContaining(subject, content, pageable);
	}
	
	// 좋아요 많은 순서 상위 4개 
	public List<Article> findTop4ByOrderByLikehitDesc() {
		return articleRepository.findTop4ByOrderByLikehitDesc();
	}

	// user id로 날짜 역순 정렬 
	public List<Article> findByUser_idOrderByCreatedDateDesc(Long userId) {
		return articleRepository.findByUser_idOrderByCreatedDateDesc(userId);
	}
	
	

	// article 게시물과 파일 저장
	public Article save(String username, Article article) {
		User user = userRepository.findByUsername(username);
		article.setUser(user);
		return articleRepository.save(article);
	}

	// article과 조인된 articlePictures를 전체 파일명을 List 형식으로 반환
	public List<String> transferFilenamepath(Article article) {

		List<ArticlePicture> articlePictures = article.getArticlePictures();
		List<String> filenames = new ArrayList<String>();

		if (!articlePictures.isEmpty()) {

			for (ArticlePicture articlePicture : articlePictures) {
				String path = articlePicture.getPath();
				String filename = articlePicture.getFilename();
				String absolutePath = new File("").getAbsolutePath() + "\\";
				String filenamePath = absolutePath + path + "/" + filename;

				filenames.add(filenamePath);
			}
		}
		return filenames;
	}

}
