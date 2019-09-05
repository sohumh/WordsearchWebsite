package com.boot.answer;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Answer {
	public int chosen;
	
	@GetMapping("/answer")
	public String randomPage(Model model) {
		chosen = 1;
		model.addAttribute("puzzle", generatePuzzle());
		model.addAttribute("macaroni", "testing");
	    return "answer";
	}

	private char[][] generatePuzzle() {
		// TODO Auto-generated method stub
		char[][] answer = new char[3][3];
		for (int r = 0; r < answer[0].length; r++) {
			for (int c = 0; c < answer.length; c++) {
				answer[r][c] = (char) ((char) r + (char) c);
			}
		}
		return answer;
	}
}




