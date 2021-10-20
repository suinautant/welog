package com.welog.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welog.www.model.ArticlePicture;

public interface ArticlePictureRepository extends JpaRepository<ArticlePicture, Long> {

//	ArticlePicture save(ArticlePicture articlePicture); 

//	List<ArticlePicture> findAllByBoardIdx(Long boardId);

}
