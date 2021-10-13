package com.welog.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welog.www.model.Article;

public interface LikeItRepository extends JpaRepository<Article, Long> {

}
