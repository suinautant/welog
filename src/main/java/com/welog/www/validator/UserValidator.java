package com.welog.www.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.welog.www.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		User user = (User) obj;
		
		// 사용자명 공백
		if (StringUtils.isEmpty(user.getUsername()))
			errors.rejectValue("username", "key", "사용자명을 입력하세요.");

		// 패스워드 공백
		if (StringUtils.isEmpty(user.getPassword()))
			errors.rejectValue("password", "key", "패스워드를 입력하세요.");
		
	}

}
