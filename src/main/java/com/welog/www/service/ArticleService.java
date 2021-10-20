package com.welog.www.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.welog.www.component.FileHandlerArticlePicture;
import com.welog.www.model.Article;
import com.welog.www.model.ArticlePicture;
import com.welog.www.model.User;
import com.welog.www.repository.ArticlePictureRepository;
import com.welog.www.repository.ArticleRepository;
import com.welog.www.repository.UserRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticlePictureRepository articlePictureRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileHandlerArticlePicture fileHandlerArticlePicture;


	public Article findById(
			Long id) {

		return articleRepository.findById(id).orElse(null);
	}
	
	public void deleteById(Long id) {
		articleRepository.deleteById(id);
	}

	public Article save(
			String username,
			Article article) {
		
		User uesr = userRepository.findByUsername(username);
		article.setUser(uesr);
		
		return articleRepository.save(article);
	}

	public void saveWithPicture(
			Article article,
			String username,
			List<MultipartFile> multipartFiles)
			throws Exception {

		// articlePicture 객체에 맞게끔 가공
		List<ArticlePicture> articlePictures = fileHandlerArticlePicture.parseFileInfo(article.getId(), multipartFiles);

		// 파일이 없는 경우
		if (articlePictures.isEmpty()) {
		}
		// 파일에 대해 DB에 저장하고 가지고 있을 것
		else {
			List<ArticlePicture> pictureBeans = new ArrayList<ArticlePicture>();
			for (ArticlePicture boardPicture : articlePictures) {
				pictureBeans.add(articlePictureRepository.save(boardPicture));
			}
//			article.setArticlePictures(pictureBeans);
		}
		save(username, article);
	}

}
