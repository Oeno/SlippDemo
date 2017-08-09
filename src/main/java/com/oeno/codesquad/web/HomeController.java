package com.oeno.codesquad.web;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oeno.codesquad.domain.Question;
import com.oeno.codesquad.domain.QuestionRepository;

@Controller
public class HomeController {
	@Autowired
	private QuestionRepository questionRepository;

	// index 화면 출력
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/index");
		
		mav.addObject("questions", reverseQuestion(questionRepository.findAll()));
		return mav;
	}
	
	// Question 목록 역순으로 변환
	public ArrayList<Question> reverseQuestion(Iterable<Question> questions) {
		ArrayList<Question> questionList = new ArrayList<>();
		
		for (Question question : questions) {
			questionList.add(question);
		}
		Collections.reverse(questionList);
		
		return questionList;
	}
}