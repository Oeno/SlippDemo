package com.oeno.codesquad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oeno.codesquad.domain.QuestionRepository;

@Controller
public class HomeController {
	@Autowired
	private QuestionRepository questionRepository;

	// index 화면 출력
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/index");
		mav.addObject("questions", questionRepository.findAll());
		return mav;
	}
}