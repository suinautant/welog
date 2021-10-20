package com.welog.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.welog.www.component.FileHandlerArticlePicture;
import com.welog.www.model.Article;
import com.welog.www.model.ArticlePicture;
import com.welog.www.repository.ArticlePictureRepository;

@Service
public class ArticlePictureService {

	@Autowired
	private ArticlePictureRepository articlePictureRepository;

	@Autowired
	private FileHandlerArticlePicture fileHandlerArticlePicture;


	public void save(
			Article article,
			List<MultipartFile> multipartFiles)
			throws Exception {

		// articlePicture 객체에 맞게끔 가공
		List<ArticlePicture> articlePictures = fileHandlerArticlePicture.parseFileInfo(article, multipartFiles);

		// 파일이 없는 경우
		if (articlePictures.isEmpty()) {
		}
		else {
			for (ArticlePicture articlePicture : articlePictures) {
				articlePicture.setArticle(article);
				articlePictureRepository.save(articlePicture);
			}
		}
	}

}
