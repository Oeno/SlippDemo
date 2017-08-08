package com.oeno.codesquad.web;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oeno.codesquad.domain.Question;
import com.oeno.codesquad.domain.QuestionRepository;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;

	// 상단 메뉴 바 점등
	private String isActive = "active";

	// 질문 등록 처리 후, index로 redirect
	@PostMapping("")
	public ModelAndView question(Question question) {
		question.setTime(getCurrentTime("yyyy-MM-dd HH:mm"));
		questionRepository.save(question);

		return new ModelAndView("redirect:/");
	}

	// 질문 상세 출력
	@GetMapping("/{index}")
	public ModelAndView show(@PathVariable Long index) {
		ModelAndView mav = new ModelAndView("qna/show");
		mav.addObject("question", questionRepository.findOne(index));
		mav.addObject("postActive", isActive);

		return mav;
	}

	// 질문 수정 페이지 출력
	@GetMapping("/{index}/form")
	public ModelAndView showUpdateForm(@PathVariable Long index) {
		ModelAndView mav = new ModelAndView("/qna/updateForm");
		mav.addObject("question", questionRepository.findOne(index));
		mav.addObject("postActive", isActive);

		return mav;
	}

	// 질문 수정 처리 후, 질문 상세 보기로 redirect
	@PostMapping("/{index}/form")
	public ModelAndView updateForm(@PathVariable Long index, Question question) {
		Question dbQuestion = questionRepository.findOne(index);
		dbQuestion.setTitle(question.getTitle());
		dbQuestion.setContents(question.getContents());

		questionRepository.save(dbQuestion);

		return new ModelAndView("redirect:/questions/{index}");
	}

	// 현재 서버시간 처리
	public static String getCurrentTime(String timeFormat) {
		return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
	}
}