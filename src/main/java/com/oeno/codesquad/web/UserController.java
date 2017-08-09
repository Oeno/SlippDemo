package com.oeno.codesquad.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oeno.codesquad.domain.User;
import com.oeno.codesquad.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	// 상단 메뉴 바 점등
	private String isActive = "active";

	// 사용자 가입 처리 후, 목록으로 redirect
	@PostMapping("")
	public ModelAndView createUser(User user) {
		userRepository.save(user);

		return new ModelAndView("redirect:/users");
	}

	// 사용자 목록 출력
	@GetMapping("")
	public ModelAndView showUsers() {
		ModelAndView mav = new ModelAndView("user/list");
		mav.addObject("users", userRepository.findAll());
		return mav;
	}

	// 사용자 정보 출력
	@GetMapping("/{index}")
	public ModelAndView showUser(@PathVariable Long index) {
		ModelAndView mav = new ModelAndView("/user/profile");
		mav.addObject("user", userRepository.findOne(index));

		return mav;
	}

	// 사용자 정보 출력 (질문 관련)
	@GetMapping("/writer/{writer}")
	public ModelAndView showUserByUserId(@PathVariable String writer) {
		ModelAndView mav = new ModelAndView("/user/profile");
		mav.addObject("user", findByUserId(writer));

		return mav;
	}

	// 사용자 정보 수정 페이지 출력
	@GetMapping("/{index}/form")
	public ModelAndView updateUserForm(@PathVariable Long index, HttpSession session) {
		if (!isMatchUser(index, session)) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("/user/updateForm");
		mav.addObject("user", userRepository.findOne(index));
		mav.addObject("userUpdateActive", isActive);

		return mav;
	}

	// 사용자 정보 수정 처리 후, 목록으로 redirect
	@PostMapping("/{index}/form")
	public ModelAndView updateUser(@PathVariable Long index, User user, HttpSession session) {
		if (!isMatchUser(index, session)) {
			return new ModelAndView("redirect:/");
		}
		User dbUser = userRepository.findOne(index);
		dbUser.update(user);
		userRepository.save(dbUser);

		return new ModelAndView("redirect:/users");
	}

	// userId로 User 검색 (존재하지 않으면 null 반환)
	public User findByUserId(String userId) {
		for (User user : userRepository.findAll()) {
			if (user.getUserId().equals(userId))
				System.out.println(user.getUserId() + " " + userId);
			return user;
		}
		return null;
	}

	// 로그인 처리
	@PostMapping("/login")
	public ModelAndView login(String userId, String password, HttpSession session) {
		User dbUser = userRepository.findByUserId(userId);

		if (dbUser == null)
			return new ModelAndView("redirect:/users/loginFailed");
		if (!dbUser.isCorrectPassword(password))
			return new ModelAndView("redirect:/users/loginFailed");

		session.setAttribute("loginedUser", dbUser);

		return new ModelAndView("redirect:/");
	}
	
	// 로그아웃 처리
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		try {
			session.removeAttribute("loginedUser");
			return new ModelAndView("redirect:/");
		} catch(Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/");
		}
	}
	

	// 유효한 사용자 검사
	public static boolean isValidUser(HttpSession session) {
		Object tempUser = session.getAttribute("loginedUser");
		if (tempUser == null) {
			return false;
		}
		
		return true;
	}
	
	// 일치하는 사용자 검사
	public static boolean isMatchUser(Long index, HttpSession session) {
		Object tempUser = session.getAttribute("loginedUser");
		if (tempUser == null) {
			return false;
		}
		final User loginedUser = (User) tempUser;
		if (!loginedUser.isCorrectIndex(index)) {
			return false;
		}
		
		return true;
	}
}