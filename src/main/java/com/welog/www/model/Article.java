package com.welog.www.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, max = 100, message = "제목은 2자 이상 100자 이하입니다.")
	private String subject;

	@NotNull
	private String content;

	private String image_src;

//	@Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime created_date;

	private LocalDateTime updated_date;

	/*
	 * CREATE TABLE `article` (
	 * `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	 * `subject` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	 * `content` TEXT NOT NULL COLLATE 'utf8_general_ci',
	 * `image_src` VARCHAR(200) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	 * `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 * `updated_date` TIMESTAMP NULL DEFAULT NULL,
	 * PRIMARY KEY (`id`) USING BTREE
	 * )
	 * COLLATE='utf8_general_ci'
	 * ENGINE=InnoDB
	 * AUTO_INCREMENT=78
	 * ;
	 * 
	 * 
	 */

}
