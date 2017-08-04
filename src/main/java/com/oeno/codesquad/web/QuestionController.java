package com.oeno.codesquad.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oeno.codesquad.domain.Question;

@Controller
public class QuestionController {
	private static List<Question> questions = new ArrayList<>();
	private static int postIndex = 1;
	
	public static List<Question> getQuestions() {
		return questions;
	}

	// 질문 등록 후, index로 redirect
	@PostMapping("/questions")
	public ModelAndView question(Question question) {
		question.setTime(getCurrentTime("YYYY-MM-DD HH:mm"));
		question.setIndex(postIndex++);
		questions.add(question);
		
		System.out.println("question size: " + questions.size() + " " + question.getIndex());

		return new ModelAndView("redirect:/");
	}
	
	// 질문 상세 보기
	@GetMapping("/questions/{index}")
	public ModelAndView show(@PathVariable int index) {
		Question showQuestion = new Question();
		
		for (Question question : questions) {
			if (question.getIndex() == index) {
				showQuestion = question;
				break;
			}
		}
		
		ModelAndView mav = new ModelAndView("qna/show");
		mav.addObject("question", showQuestion);
		
		return mav;
	}
	
	@GetMapping("/questions")
	public ModelAndView questionForm() {
		return new ModelAndView("qna/form");
	}
    
	// 현재 시간 구함
    public static String getCurrentTime(String timeFormat){
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
    }
}
