package com.welog.www.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welog.www.component.FileHandlerArticlePicture;
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

	// article id로 찾기
	public Article findById(
			Long id) {

		return articleRepository.findById(id).orElse(null);
	}

	// article id로 삭제
	public void deleteById(
			Long id) {

		Article      article   = articleRepository.findById(id).orElse(null);
		List<String> filenames = transferFilenamepath(article);

		// 첨부 파일 삭제
		for (String filename : filenames) {
			deleteFileByDeleteArticle(filename);
		}

		// article 게시물 삭제
		articleRepository.deleteById(id);
	}
	
	// user id로 article 모두 삭제
	public void deleteByUserId(Long userId) {
		
		// 사용자가 작성한 모든 게시물
		List<Article> articles = userService.getArticlesFindById(userId);
		
		// 모든 게시물 삭제
		for (Article article : articles) {
			deleteById(article.getId());
		}

	}

	// article 삭제 할 때 첨부 된 파일 삭제
	public void deleteFileByDeleteArticle(
			String filenamePath) {

		File deleteFile = new File(filenamePath);

		if (deleteFile.exists()) {
			deleteFile.delete();
		} else {
		}
	}

	// article 게시물과 파일 저장
	public Article save(
			String username,
			Article article) {

		User uesr = userRepository.findByUsername(username);
		article.setUser(uesr);

		return articleRepository.save(article);
	}

	// article과 조인된 articlePictures를 전체 파일명을 List 형식으로 반환
	public List<String> transferFilenamepath(
			Article article) {

		List<ArticlePicture> articlePictures = article.getArticlePictures();
		List<String>         filenames       = new ArrayList<String>();

		if (!articlePictures.isEmpty()) {

			for (ArticlePicture articlePicture : articlePictures) {
				String path         = articlePicture.getPath();
				String filename     = articlePicture.getFilename();
				String absolutePath = new File("").getAbsolutePath() + "\\";
				String filenamePath = absolutePath + path + "/" + filename;

				filenames.add(filenamePath);
			}
		}
		return filenames;
	}

}
