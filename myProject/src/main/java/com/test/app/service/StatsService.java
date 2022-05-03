package com.test.app.service;

import java.util.ArrayList;

import com.test.app.model.StatsVO;

public interface StatsService {
	public void insertStats(StatsVO vo);
	public ArrayList<StatsVO> getStatsList(StatsVO vo);
	public void deleteStats(StatsVO vo);
}
