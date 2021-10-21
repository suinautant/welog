package com.welog.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.welog.www.model.Article;
import com.welog.www.model.User;
import com.welog.www.repository.LikeItRepository;

@Service
public class LikeItService {

	@Autowired
	private LikeItRepository likeItRepository;

	// 게시물의 좋아요 카운트
	public int countLikeUser(Article article) {
		List<User> likeUsers = article.getLikeUsers();
		return likeUsers.size();
	}
	
	// 사용자별(user id) 좋아요 카운트
	public int countLikeByUser(Long userId) {
		return likeItRepository.countLikeByUser(userId);
	}

	// 접속자가 해당 게시물 좋아요 선택했는지 유무
	public boolean isLikeUser(Article article, Authentication authentication) {
		List<User> likeUsers = article.getLikeUsers();
		for (User likeUser : likeUsers) {

			String currentUser = authentication.getName();
			if (currentUser.equals(likeUser.getUsername())) {
				return true;
			}
		}
		return false;
	}
	
	// 사용자별 좋아요를 누른 게시물을 작성일 역순으로 정렬해 LIst<Article)로 반환
	public List<Article> findByLikeItUserOrderByCreatedDateDesc(Long userId) {
		return likeItRepository.findByLikeItUserOrderByCreatedDateDesc(userId);
	}

	// 좋아요 추가
	public void likeItAdd(Long articleId, Long userId) {
		likeItRepository.likeItAdd(articleId, userId);
	}

	// 좋아요 추가/제거시 게시물의 likehit 컬럼에 적용
	public void likeItCount(Long articleId, Long likehit) {
		likeItRepository.likeItCount(articleId, likehit);
	}
	
	// 좋아요 제거
	public void likeItRemove(Long articleId, Long userId) {
		likeItRepository.likeItRemove(articleId, userId);
	}

}
