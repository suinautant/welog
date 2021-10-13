package com.welog.www.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.welog.www.classObject.LikeIt;
import com.welog.www.model.Article;
import com.welog.www.model.User;

@Service
public class LikeItService {

	public boolean isLikeUser(Article article, Authentication authentication) {

		List<User> likeUsers = article.getLikeUsers();
		for (User likeUser : likeUsers) {

			String currentUser = authentication.getName();
			if (currentUser.equals(likeUser.getUsername())) {
//				likeIt.setLikeUser(true);
				// FOR-TEST
//				System.out.println("$$$$$$$$$$ must true : " + likeIt.isLikeUser());
				return true;
			}
		}

//		likeIt.setLikeUser(false);
		return false;
	}

	public int countLikeUser(Article article) {

		List<User> likeUsers = article.getLikeUsers();
		return likeUsers.size();

	}

}
