package com.cos.newscrawling.batch;

import org.springframework.scheduling.annotation.Scheduled;

// @Component
public class TestBatch {

	int num = 1;
	
	// 초 분 시 일 월 주
	@Scheduled(cron="0 * * * * *", zone="Asia/Seoul")
	public void testCount() {
		System.out.println("숫자 카운트:" +num);
		num++;
	}
}
