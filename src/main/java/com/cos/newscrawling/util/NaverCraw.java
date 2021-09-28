package com.cos.newscrawling.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.newscrawling.domain.News;

@Component
public class NaverCraw {

	int aidNum = 1; 
	
	public List<News> collect10() {
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();
		
		for (int i = 1; i < 6; i++) {
			String aid = String.format("%010d", aidNum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid="+aid;
			String html = rt.getForObject(url, String.class);
			
			Document doc = Jsoup.parse(html);
			
			Element titleElement = doc.selectFirst("#articleTitle");
			Element createAtElement = doc.selectFirst(".t11");
			String title = titleElement.text();
			String createAt = createAtElement.text();
			
			News news = News.builder().title(title).createAt(createAt).build();
			
			newsList.add(news);
			
			aidNum ++;
		}
		return newsList;
	}
}
