package com.welog.www.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 아이디
//	@UniqueElements
	private String username;

	// 패스워드
	private String password;

	// 활성화
	private Boolean enabled;

	// 다대다 조인
	// User 객체에서 Role 객체 제어
	@ManyToMany
	@JoinTable(name = "user_role", 
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	

	// cascade : User 객체로 CRUD시 Board 객체도 함께 CRUD 할 때 사용
	// cascade = CascadeType.ALL,
	// orphanRemoval : 부모가 없는 객체는 삭제 (true)
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private List<Article> boards = new ArrayList<>();

	//	// cascade : User 객체로 CRUD시 Board 객체도 함께 CRUD 할 때 사용
	//	// orphanRemoval : 부모가 없는 객체는 삭제 (true)
	//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	//	private List<Board> boards = new ArrayList<>();

	/*
	 * CREATE TABLE `user` (
	 * `id` INT(10) NOT NULL AUTO_INCREMENT,
	 * `username` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	 * `password` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	 * `enabled` BIT(1) NULL DEFAULT NULL,
	 * PRIMARY KEY (`id`) USING BTREE
	 * )
	 * COLLATE='utf8_general_ci'
	 * ENGINE=InnoDB
	 * ;
	 */
}
