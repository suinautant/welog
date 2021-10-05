package com.welog.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welog.www.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	

}
