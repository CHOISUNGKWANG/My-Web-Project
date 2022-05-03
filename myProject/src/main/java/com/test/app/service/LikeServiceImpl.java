package com.test.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.model.LikeDAO;
import com.test.app.model.LikeVO;

@Service("likeService")
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDAO likeDAO;
	
	@Override
	public void insertLike(LikeVO vo) {
		likeDAO.insertLike(vo);
	}

	@Override
	public LikeVO getLike(LikeVO vo) {
		return likeDAO.getLike(vo);
	}

	@Override
	public void deleteLike(LikeVO vo) {
		likeDAO.deleteLike(vo);
	}

}
