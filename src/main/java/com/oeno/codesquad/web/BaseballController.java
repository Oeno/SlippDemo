package com.oeno.codesquad.web;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseballController {
	static ArrayList<Integer> generatedAnswer = generateAnswer();

	// 파라미터 이름이 같아야 함.
	@GetMapping("/baseball/result")
	public ModelAndView input(String guessedAnswer) {
		// 앞에 경로같은 거 안 줘도 됨. (templates dir에서 찾음)
		// application.properties에 suffix 설정한 것이랑 합쳐서 파일 찾아감.
		ModelAndView mav = new ModelAndView("baseball/result");

		String feedback = checkBalls(generatedAnswer, guessAnswer(guessedAnswer));

		mav.addObject("feedback", feedback);
		System.out.println("guessed Answer: " + guessedAnswer);
		
		return mav;
	}
	
	@GetMapping("/baseball/newgame")
	public ModelAndView newgame(String inputValue) {
		generatedAnswer = generateAnswer();
		
		// 애는 html 빼는 거 아님.
		ModelAndView mav = new ModelAndView("redirect:/baseball");
		
		return mav;
	}

	static ArrayList<Integer> generateAnswer() {
		List<Integer> possibleBalls = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Collections.shuffle(possibleBalls);

		ArrayList<Integer> computerBalls = new ArrayList<>(3);
		computerBalls.add(possibleBalls.get(0));
		computerBalls.add(possibleBalls.get(1));
		computerBalls.add(possibleBalls.get(2));
		
		System.out.println("generated Answer: " + computerBalls);
		return computerBalls;
	}

	public ArrayList<Integer> guessAnswer(String inputValue) {
		ArrayList<Integer> userBalls = new ArrayList<>();
		
		for (char num : inputValue.toCharArray())
			userBalls.add(num - '0');
		
		return userBalls;
	}

	public int checkBall(ArrayList<Integer> generatedBalls, int userBall, int position) {
		if (!generatedBalls.contains(userBall)) {
			return 0;
		}

		int computerBall = generatedBalls.get(position);
		if (userBall == computerBall) {
			return 2;
		}

		return 1;
	}

	public String checkBalls(ArrayList<Integer> computerBalls, ArrayList<Integer> userBalls) {
		int strike = 0;
		int ball = 0;
		for (int i = 0; i < userBalls.size(); i++) {
			int result = checkBall(computerBalls, userBalls.get(i), i);

			if (result == 2) {
				strike++;
			} else if (result == 1) {
				ball++;
			}
		}
		if (strike == 3)
			return String.format("축하합니다! 정답입니다.");

		return String.format("결과 : %d strike, %d ball", strike, ball);
	}
}