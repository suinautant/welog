package com.welog.www.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.welog.www.model.Article;

@Component
public class ArticleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Article.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		Article article = (Article) obj;

		// 제목 공백 일 경우 
		if (StringUtils.isEmpty(article.getSubject()))
			errors.rejectValue("subject", "key", "제목을 입력하세요.");

		// 내용 공백 일 경우 
		if (StringUtils.isEmpty(article.getContent()))
			errors.rejectValue("content", "key", "내용을 입력하세요.");
	}

}
