package com.test.app.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.app.model.StatsVO;

public class Crawling {
	public ArrayList<StatsVO> crawling(String key) {
		final String url="https://poro.gg/champions?format=stats";
		Document doc=null;
		
		try {
			doc=Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Elements eles=doc.select("div.champion-sub-list__content[data-role-key="+key+"] div.champion-sub-list__item"); 
		Iterator<Element> itr=eles.iterator();
		Iterator<Element> itr2=eles.select("img").iterator();
		Iterator<Element> itr3=eles.select("div.rank").iterator();
		Iterator<Element> itr4=eles.select("div.champion").iterator();
		Iterator<Element> itr5=eles.select("div.rate").iterator();
		
		itr3.next();
		itr4.next();
		itr5.next();
		itr5.next();
		itr5.next();
		itr5.next();
		int i = 0;
		ArrayList<StatsVO> datas=new ArrayList<StatsVO>();
		while(itr.hasNext()) {
			i++;
			
			String tag1 = String.valueOf(itr2.next());
			String[] arr1 = tag1.split("\"");
			String tag2 = String.valueOf(itr2.next());
			String[] arr2 = tag2.split("\"");
			
			String rank = itr3.next().text();
			String champImg = arr1[1];
			String champ = itr4.next().text();
			String tierImg = arr2[1];
			String winRate = itr5.next().text();
			String pickRate = itr5.next().text();
			
			itr5.next();
			
			StatsVO data = new StatsVO();
			data.setSposition(key);
			data.setSrank(rank);
			data.setSchampsrc(champImg);
			data.setSchamp(champ);
			data.setStiersrc(tierImg);
			data.setSwinrate(winRate);
			data.setSpickrate(pickRate);
			datas.add(data);
			
			if(i == 10) {
				break;
			}
		}
		System.out.println("로그: 크롤링 성공");
		return datas;
	}
}	
