package com.company.yedam.config;

import javax.inject.Singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.yedam.di.AppleSpeaker;
import com.company.yedam.di.LgTV;
import com.company.yedam.di.SamsungTV;
import com.company.yedam.di.SonySpeaker;
import com.company.yedam.di.Speaker;
import com.company.yedam.di.TV;

//@Configuration
public class AppConfig {
	@Bean
	public Speaker speaker() {
		return new AppleSpeaker();
	}
	@Bean
	@Singleton
	public TV tvSvc() {
		SamsungTV tv = new SamsungTV();
		tv.setSpeaker(speaker());
		return tv;
	}
}
