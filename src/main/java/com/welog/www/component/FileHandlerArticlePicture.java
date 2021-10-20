package com.welog.www.component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.welog.www.model.Article;
import com.welog.www.model.ArticlePicture;
import com.welog.www.service.ArticleService;

@Component
public class FileHandlerArticlePicture {

	@Autowired
	private ArticleService articleService;
	
	private String savePath = "src/main/resources/static/data/files/";

	public List<ArticlePicture> parseFileInfo(
			Long articleId,
			List<MultipartFile> multipartFiles)
			throws Exception {

		List<ArticlePicture> fileList = new ArrayList<>();

		if (multipartFiles.isEmpty()) {
			return fileList;
		}

		// 파일 이름 변경 : yyyyMMdd 형식
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String currentDate = simpleDateFormat.format(new Date());

		// 프로젝트 폴더의 절대 경로, 저장 날짜 폴더에 각각 저장
		String absolutePath = new File("").getAbsolutePath() + "\\";
//		String path = "src/main/resources/static/images/" + currentDate;
		String path = savePath + currentDate;
		File filePath = new File(path);

		// 디렉토리 없다면, 상위 디렉토리까지 모두 생성
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		for (MultipartFile multipartFile : multipartFiles) {

			// 빈 파일인지 확인
			if (!multipartFile.isEmpty()) {
				String contentType = multipartFile.getContentType();
				String originalFileExtension;

				// 그림 파일만 저장
				if (ObjectUtils.isEmpty(contentType)) {
					break;
				} else {
					if (contentType.contains("image/jpeg")) {
						originalFileExtension = ".jpg";
					} else if (contentType.contains("image/png")) {
						originalFileExtension = ".png";
					} else if (contentType.contains("image/gif")) {
						originalFileExtension = ".gif";
					} else {
						break;
					}
				}

				// 파일 이름을 나노초로 지정
				String newFilename = Long.toString(System.nanoTime()) + originalFileExtension;
				ArticlePicture articlePicture = new ArticlePicture();
				Article article = articleService.findById(articleId);

				// 객체 생성
				articlePicture.setFilename(newFilename);
				articlePicture.setPath(path);
				articlePicture.setOriginal(multipartFile.getOriginalFilename());
				articlePicture.setSize(multipartFile.getSize());
				articlePicture.setArticle(article);

				// 반환 할 리스트에 추가
				fileList.add(articlePicture);

				// 이름 변경해 파일로 저장
				File file = new File(absolutePath + path + "/" + newFilename);
				multipartFile.transferTo(file);
			}
		}
		return fileList;
	}
}