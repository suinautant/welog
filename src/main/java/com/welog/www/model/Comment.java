package com.welog.www.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;

//	private String username;
	
	@Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime created_date;
	
	// article 객체 N:1 외래키 조인
	@ManyToOne
	@JoinColumn(name = "article_id")
	@JsonIgnore
	private Article article;	
	
	// user 객체 N:1 외래키 조인
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	

}
