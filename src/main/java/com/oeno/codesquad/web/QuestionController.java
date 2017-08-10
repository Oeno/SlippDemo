package com.oeno.codesquad.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oeno.codesquad.domain.CommentRepository;
import com.oeno.codesquad.domain.Question;
import com.oeno.codesquad.domain.QuestionRepository;
import com.oeno.codesquad.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	// 상단 메뉴 바 점등
	private String isActive = "active";

	// 질문 등록 페이지 출력
	@GetMapping("/form")
	public ModelAndView showQuestionForm(HttpSession session) {
		if (!UserController.isValidUser(session)) {
			return new ModelAndView("redirect:/");
		}

		return new ModelAndView("/qna/form");
	}

	// 질문 등록 처리 후, index로 redirect
	@PostMapping("")
	public ModelAndView question(Question question, HttpSession session) {
		if (!UserController.isValidUser(session)) {
			return new ModelAndView("redirect:/");
		}
		
		Question dbQuestion = new Question((User)session.getAttribute("loginedUser"), question.getTitle(),
				question.getContents());
		questionRepository.save(dbQuestion);

		return new ModelAndView("redirect:/");
	}

	// 질문 상세 출력
	@GetMapping("/{index}")
	public ModelAndView show(@PathVariable Long index, HttpSession session) {
		if (!UserController.isValidUser(session)) {
			return new ModelAndView("redirect:/");
		}
		
		ModelAndView mav = new ModelAndView("qna/show");
		mav.addObject("question", questionRepository.findOne(index));
		mav.addObject("comments", commentRepository.findByQuestionIndexOrderByIndex(index));
		mav.addObject("postActive", isActive);

		return mav;
	}

	// 질문 수정 페이지 출력
	@GetMapping("/{index}/form")
	public ModelAndView showUpdateForm(@PathVariable Long index, HttpSession session) {
		Question dbQuestion = questionRepository.findOne(index);
		
		if (!UserController.isMatchUser(dbQuestion.getUser().getIndex(), session)) {
			return new ModelAndView("redirect:/questions/{index}");
		}
		
		ModelAndView mav = new ModelAndView("/qna/updateForm");
		mav.addObject("question", dbQuestion);
		mav.addObject("postActive", isActive);

		return mav;
	}

	// 질문 수정 처리 후, 질문 상세 보기로 redirect
	@PostMapping("/{index}/form")
	public ModelAndView updateForm(@PathVariable Long index, Question question, HttpSession session) {
		Question dbQuestion = questionRepository.findOne(index);
		
		if (!UserController.isMatchUser(dbQuestion.getUser().getIndex(), session)) {
			return new ModelAndView("redirect:/questions/{index}");
		}
		
		dbQuestion.update(question);
		questionRepository.save(dbQuestion);

		return new ModelAndView("redirect:/questions/{index}");
	}
	
	// 질문 삭제 처리
	@DeleteMapping("/{index}")
	public ModelAndView delete(@PathVariable Long index, HttpSession session) {
		Question dbQuestion = questionRepository.findOne(index);
		
		if (!UserController.isMatchUser(dbQuestion.getUser().getIndex(), session)) {
			return new ModelAndView("redirect:/questions/{index}");
		}

		questionRepository.delete(index);
		return new ModelAndView("redirect:/");
	}
}