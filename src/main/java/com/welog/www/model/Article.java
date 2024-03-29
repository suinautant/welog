package com.welog.www.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String subject;

	@NotNull
	private String content;

	private Long likehit;

	@Column(name="created_date", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	// 다대다 조인 : 좋아요 선택한 사용자
	// Article 객체에서 User 객체 제어
	@ManyToMany
	@JoinTable(name = "like_article_user", 
		joinColumns = @JoinColumn(name = "article_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> likeUsers = new ArrayList<>();
	
	// article_id에 의한 댓글 목록
	@OneToMany(mappedBy = "article", orphanRemoval = true)
	@OrderBy("created_date DESC")
	private List<Comment> comments = new ArrayList<>();

	// ArticlePicture 1:N
	@OneToMany(mappedBy = "article", orphanRemoval = true)
	private List<ArticlePicture> articlePictures = new ArrayList<>();

}
