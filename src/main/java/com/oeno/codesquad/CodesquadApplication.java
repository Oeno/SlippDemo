package com.oeno.codesquad;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodesquadApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CodesquadApplication.class, args);
	}
	
	// 현재 서버시간 처리
	public static String getCurrentTime(String timeFormat) {
		return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
	}
}