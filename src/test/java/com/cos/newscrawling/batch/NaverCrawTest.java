package com.cos.newscrawling.batch;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

// 주소: https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid=0000000001
public class NaverCrawTest {

	int aidNum = 1;
	
	// @Test
	public void test1() {
		RestTemplate rt = new RestTemplate();
		
		String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid=0000000001";
		
		String html = rt.getForObject(url, String.class);
		System.out.println(html);
		
		// Jsoup로 ArticleTitle 파싱
		Document doc = Jsoup.parse(html);
		
		Element titleElement = doc.selectFirst("#articleTitle");
		String title = titleElement.text();
		
		System.out.println(title);
	}
	
	// @Test
	public void test2() {
		for (int i = 1; i < 11; i++) {
			System.out.println(String.format("%010d", i));
		}
	}
	
	@Test
	public void test3() {
		RestTemplate rt = new RestTemplate();
		List<NewsTest> nts = new ArrayList<>();
		
		for (int i = 1; i < 11; i++) {
			String aid = String.format("%010d", aidNum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid="+aid;
			String html = rt.getForObject(url, String.class);
			
			Document doc = Jsoup.parse(html);
			
			Element titleElement = doc.selectFirst("#articleTitle");
			Element timeElement = doc.selectFirst(".t11");
			String title = titleElement.text();
			String time = timeElement.text();
			
			System.out.println(title);
			System.out.println(time);
			
			NewsTest nt = NewsTest.builder().title(title).time(time).build();
			
			nts.add(nt);
			
			aidNum ++;
		}
		assertTrue(nts.size() == 10);
	}
}
