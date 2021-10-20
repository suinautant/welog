package com.welog.www.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.welog.www.component.FileHandlerArticlePicture;
import com.welog.www.model.Article;
import com.welog.www.model.ArticlePicture;
import com.welog.www.repository.ArticlePictureRepository;
import com.welog.www.repository.ArticleRepository;

@Service
public class ArticlePictureService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticlePictureRepository articlePictureRepository;

	@Autowired
	private FileHandlerArticlePicture fileHandlerArticlePicture;

	public Article addBoard(
			Article article,
			List<MultipartFile> files)
			throws Exception {
		// 파일을 저장하고 그 BoardPicture 에 대한 list 를 가지고 있는다
		List<ArticlePicture> list = fileHandlerArticlePicture.parseFileInfo(article.getId(), files);

		if (list.isEmpty()) {
			// TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
		}
		// 파일에 대해 DB에 저장하고 가지고 있을 것
		else {
			List<ArticlePicture> pictureBeans = new ArrayList<>();
			for (ArticlePicture boardPicture : list) {
				pictureBeans.add(articlePictureRepository.save(boardPicture));
			}
			article.setArticlePictures(pictureBeans);
		}

//		article.setReported_date(new Date().toString());

		return articleRepository.save(article);
	}

}
