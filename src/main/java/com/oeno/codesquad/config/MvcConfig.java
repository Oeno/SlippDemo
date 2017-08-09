package com.oeno.codesquad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

// 간단한 View 출력
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

		// BaseballController
		registry.addViewController("/baseball").setViewName("/baseball/form");

		// UserControlller
		registry.addViewController("/users/signup").setViewName("/user/form");
		registry.addViewController("/users/loginForm").setViewName("/user/login");
		registry.addViewController("/users/loginFailed").setViewName("/user/login_failed");

		// QuestionController
	}
}