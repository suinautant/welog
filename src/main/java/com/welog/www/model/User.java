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


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 아이디
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

	// orphanRemoval : 부모가 없는 객체는 삭제 (true)
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@JsonIgnore
	private List<Article> articles = new ArrayList<>();

	// 사용자가 좋아요한 Article
	@ManyToMany(mappedBy = "likeUsers")
	@JsonIgnore
	private List<Article> likeArticle;
	
	// article_id에 의한 댓글 목록
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

}
