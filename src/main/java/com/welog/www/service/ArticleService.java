package com.welog.www.service;

import java.io.File;
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
	private FileHandlerArticlePicture fileHandlerArticlePicture;

	public Article findById(
			Long id) {

		return articleRepository.findById(id).orElse(null);
	}

	public void deleteById(
			Long id) {

		Article article = articleRepository.findById(id).orElse(null);

		List<ArticlePicture> articlePictures = article.getArticlePictures();
		if (!articlePictures.isEmpty()) {
			System.out.println("$$$$$$$$$$$$$$ articlePicture.isEmpty : 비어 있지 않음");

			for (ArticlePicture articlePicture : articlePictures) {
				String path         = articlePicture.getPath();
				String filename     = articlePicture.getFilename();
				String absolutePath = new File("").getAbsolutePath() + "\\";
				String filenamePath = absolutePath + path + "/" + filename;

				System.out.println("$$$$$$$$$$$$$$ articlePicture : " + articlePicture);
				System.out.println("$$$$$$$$$$$$$$ articlePicture filename : " + filename);
				System.out.println("$$$$$$$$$$$$$$ articlePicture fileNamePath: " + filenamePath);

				deleteFileByDeleteArticle(filenamePath);
			}
		}
		articleRepository.deleteById(id);
	}

	public void deleteFileByDeleteArticle(
			String filenamePath) {

		File deleteFile = new File(filenamePath);

		if (deleteFile.exists()) {
			deleteFile.delete();
			System.out.println("파일을 삭제하였습니다.");
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
	}

	public Article save(
			String username,
			Article article) {

		User uesr = userRepository.findByUsername(username);
		article.setUser(uesr);

		return articleRepository.save(article);
	}

}
