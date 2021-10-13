package com.welog.www.classObject;

public class LikeIt {

	private boolean isLikeUser;

	private int countLikeUser;

	public LikeIt() {

	}

	public LikeIt(boolean isLikeUser, int countLikeUser) {
		this.isLikeUser    = isLikeUser;
		this.countLikeUser = countLikeUser;
	}

	public boolean isLikeUser() {
		return isLikeUser;
	}

	public void setLikeUser(boolean isLikeUser) {
		this.isLikeUser = isLikeUser;
	}

	public int getCountLikeUser() {
		return countLikeUser;
	}

	public void setCountLikeUser(int countLikeUser) {
		this.countLikeUser = countLikeUser;
	}

}
