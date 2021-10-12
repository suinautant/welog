package com.welog.www.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.welog.www.model.Role;
import com.welog.www.model.User;
import com.welog.www.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
	public Long getUserIdFindByUsername(String username) {
		return userRepository.findByUsername(username).getId();
	}

}
