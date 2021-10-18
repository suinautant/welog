package com.welog.www.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.welog.www.model.Comment;

@Component
public class CommentValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Comment.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		Comment comment = (Comment) obj;
		
	}
	
	

}
