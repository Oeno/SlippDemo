package com.oeno.codesquad.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oeno.codesquad.domain.User;

@Controller
public class UserController {
	private static List<User> users = new ArrayList<>();
	
	// 자동으로 User 클래스의 속성을 탐색해 매칭시킴.
	@PostMapping("/users")
	public ModelAndView create(User user) {
		users.add(user);
		System.out.println("size : " + users.size());

		return new ModelAndView("redirect:/users");
	}
	
	// get: 서버의 데이터 조회
	@GetMapping("/users")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("user/list");
		mav.addObject("users", users);
		return mav;
	}
	
	// @PathVariable -> 동적인 값 할당.
	@GetMapping("/users/{userId}")
	public ModelAndView show(@PathVariable String userId) {
		User correctUser = new User();
		
		for (User user : users) {
			if (user.getUserId().equals(userId)) {
				correctUser = user;
				break;
			}
		}

		ModelAndView mav = new ModelAndView("user/profile");
		
		mav.addObject("user", correctUser);
		
		return mav;
	}
	
	@GetMapping("/user/signup")
	public ModelAndView signup() {
		return new ModelAndView("user/form");
	}
	
	@GetMapping("/user/login")
	public ModelAndView login() {
		return new ModelAndView("/user/login");
	}
	
	@GetMapping("/user/loginFailed")
	public ModelAndView loginFailed() {
		return new ModelAndView("/user/login_failed");
	}
}