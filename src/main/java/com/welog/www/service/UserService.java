package com.welog.www.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.welog.www.model.Article;
import com.welog.www.model.Role;
import com.welog.www.model.User;
import com.welog.www.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// user id로 user 객체 찾기
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	// 현 접속자 이름으로 user id 찾기
	public Long findUserIdByCurrentUsername(Authentication authentication) {
		String username = authentication.getName();
		return getUserIdFindByUsername(username);
	}
	
	// user id로 작성한 모든 article 반환
	public List<Article> getArticlesFindById(Long userId) {
		User user =  userRepository.findById(userId).orElse(null);
		return user.getArticles();
	}

	// 사용자 이름으로 user id 찾기
	public Long getUserIdFindByUsername(String username) {
		return userRepository.findByUsername(username).getId();
	}
	
	// 사용자 가입 - 저장
	public User save(User user) {
		
		// 패스워드 암호화
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
		// 계정 활성화 설정
		user.setEnabled(true);
		
		// ManytoMany 조인 된 user와 role
		// user 통해 role 설정
		Role role = new Role();
		// role.id, role.name =>  1, "ROLE_USER"
		role.setId(1l);
		user.getRoles().add(role);
		
		return userRepository.save(user);
	}

	

}
