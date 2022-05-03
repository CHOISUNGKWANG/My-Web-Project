package com.test.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.model.StatsDAO;
import com.test.app.model.StatsVO;

@Service("statsService")
public class StatsServiceImpl implements StatsService {

	@Autowired 
	private StatsDAO statsDAO;
	
	@Override
	public void insertStats(StatsVO vo) {
		statsDAO.insertStats(vo);
	}

	@Override
	public ArrayList<StatsVO> getStatsList(StatsVO vo) {
		return statsDAO.getStatsList(vo);
	}

	@Override
	public void deleteStats(StatsVO vo) {
		statsDAO.deleteStats(vo);
	}

}
