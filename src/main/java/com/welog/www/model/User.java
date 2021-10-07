package com.welog.www.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;

	private Boolean enabled;

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
