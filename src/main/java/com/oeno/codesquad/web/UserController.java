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
	private String isActive = "active";
	
	// 자동으로 User 클래스의 속성을 탐색해 매칭시킴.
	@PostMapping("/users")
	public ModelAndView create(User user) {
		if (isOverlap(user.getUserId())) {
			return null;
		} else {
			users.add(user);
			System.out.println("size : " + users.size());
		}

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

		ModelAndView mav = new ModelAndView("/user/profile");
		mav.addObject("user", correctUser);
		
		return mav;
	}
	
	// 개인정보수정 페이지 띄움
	@GetMapping("/users/{userId}/form")
	public ModelAndView showUpdateForm(@PathVariable String userId) {
		User correctUser = new User();
		
		for (User user : users) {
			if (user.getUserId().equals(userId)) {
				correctUser = new User(user);
				break;
			}
		}
		
		ModelAndView mav = new ModelAndView("/user/updateForm");
		mav.addObject("user", correctUser);
		mav.addObject("userUpdateActive", isActive);
		
		return mav;
	}
	
	// 개인정보수정 등록 (복제 방식 수정해야 )
	@PostMapping("/users/{userId}/form")
	public ModelAndView updateForm(User user) {
		for (User checkUser : users) {
			if (checkUser.getUserId().equals(user.getUserId())) {
				checkUser.setPassword(user.getPassword());
				checkUser.setName(user.getName());
				checkUser.setEmail(user.getEmail());
				break;
			}
		}

		return new ModelAndView("redirect:/users");
	}
	
	public boolean isOverlap(String userId) {
		for (User user : users) {
			if (userId.equals(user.getUserId())) {
				return true;
			}
		}
		return false;
	}
}