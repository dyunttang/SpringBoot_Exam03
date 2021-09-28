package com.cos.newscrawling.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class NewsTest {
	private String title;
	private String time;
}
