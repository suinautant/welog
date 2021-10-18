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
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Data
@Getter
@Setter
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
//	@Size(min = 2, max = 100, message = "제목은 2자 이상 100자 이하입니다.")
	private String subject;

	@NotNull
	private String content;

	private String image_src;
	
	private Long likehit;

//	@Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime created_date;

	private LocalDateTime updated_date;
	
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
}
