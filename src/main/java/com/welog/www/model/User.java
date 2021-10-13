package com.welog.www.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Data
@Getter
@Setter
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
//	@ManyToMany(fetch = FetchType.LAZY)
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

	// 사용자가 좋아요한 Article
	@ManyToMany(mappedBy = "likeUsers")
	@JsonIgnore
	private List<Article> likeArticle;

}
