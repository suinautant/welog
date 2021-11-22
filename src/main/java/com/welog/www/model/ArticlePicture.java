package com.welog.www.model;

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
public class ArticlePicture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String filename;
	
	private String path;
	
	private String original;

	private Long size;
	
	// article 객체 N:1 외래키 조인
	@ManyToOne
	@JoinColumn(name = "article_id")
	@JsonIgnore
	private Article article;

	
}
