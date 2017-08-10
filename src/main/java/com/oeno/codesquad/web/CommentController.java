package com.oeno.codesquad.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oeno.codesquad.domain.Comment;
import com.oeno.codesquad.domain.CommentRepository;
import com.oeno.codesquad.domain.QuestionRepository;
import com.oeno.codesquad.domain.User;

@Controller
@RequestMapping("/questions")
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private QuestionRepository questionRepository;

	// 답글 등록 처리
	@PostMapping("/{questionIndex}/comments")
	public ModelAndView comment(@PathVariable Long questionIndex, String contents, HttpSession session) {
		if (!UserController.isValidUser(session)) {
			return new ModelAndView("redirect:/questions/{questionIndex}");
		}

		Comment dbComment = new Comment(questionRepository.findOne(questionIndex),
				(User) session.getAttribute("loginedUser"), contents);
		commentRepository.save(dbComment);

		return new ModelAndView("redirect:/questions/{questionIndex}");
	}
}
