package com.welog.www.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ArticlePicture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long article_id;
	
	private String original_filename;
	
	private String stored_filename;
	
	private String stored_filepath;
	
	private Long filesize;
	
	
	
}
