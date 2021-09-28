package com.cos.newscrawling.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.newscrawling.domain.News;
import com.cos.newscrawling.domain.NewsRepository;
import com.cos.newscrawling.util.NaverCraw;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NewsBatch {

	private final NewsRepository newsRepository;
	private final NaverCraw naverCraw;
	
	@Scheduled(fixedDelay=1000*60*1)
	public void newsCrawAndSave() {
		List<News> newsList = naverCraw.collect10();
		newsRepository.saveAll(newsList);
	}
}
