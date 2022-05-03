package com.test.app.service;

import com.test.app.model.LikeVO;

public interface LikeService {
	public void insertLike(LikeVO vo);
	public LikeVO getLike(LikeVO vo);
	public void deleteLike(LikeVO vo);
}
