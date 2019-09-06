package com.boot.specifications;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.boot.specifications.alg.NormalWordSearch;
import com.boot.specifications.alg.ProvidedWordsearch;
import com.boot.specifications.alg.Wordsearch;

@Controller
public class Specfications {
	public Spec spec;

	@GetMapping("/enteredPage")
	public String enteredPage(Model model) {
		spec = new Spec();
	    model.addAttribute("spec", spec);
	    return "enteredPage";
	}
	
	@PostMapping("/enteredPage")
    public String enteredSubmit(Spec spec, Model model) {
		spec.type = 3;
		spec.wordList = string_to_list(spec.getWords());
		spec.setMinLength(enteredMinimumLength(spec));
		if (meetsValidation(spec.words)) {
			model.addAttribute("length", spec.minLength);
			model.addAttribute("message", new Message());
			Storage.spec = spec;
			return "hiddenMessage";
		}
		return "enteredPageE";
    }

	private boolean meetsValidation(String words) {
		int c = 0;
		String curr = "";
        words = words.toLowerCase();
        HashSet<String> seen = new HashSet<>();
        for (int i = 0; i < words.length(); i++) {
            char a = words.charAt(i);
            int val = Character.getNumericValue(a);
            if ((val < 10 || val > 35) && a != ' ') {
                continue;
            }
            if (a == ' ') {
            	if (curr.length() <= 1 || seen.contains(curr) || curr.length() >= 10) {
            		return false;
            	}
            	seen.add(curr);
            	c++;
            	curr = "";
            	continue;
            }
            curr += a;

        }
        return c > 3 && curr.length() > 1 && !seen.contains(curr);
	}

	@GetMapping("/randomPage")
	public String randomPage(Model model) {
		spec = new Spec();
	    model.addAttribute("spec", spec);
	    return "randomPage";
	}
	
	@PostMapping("/randomPage")
    public String randomSubmit(Spec spec, Model model) {
		spec.type = 1;
		spec.answer = randomBool();
		spec.setMinLength(assignMinimumLength(spec));
		Storage.spec = spec;
		model.addAttribute("message", new Message());
		model.addAttribute("length", spec.minLength);
        return "hiddenMessage";
    }

	@GetMapping("/categoriesPage")
	public String categoriesPage(Model model) {
		spec = new Spec();
	    model.addAttribute("spec", spec);
	    model.addAttribute("categs", generateCategories());
	    return "categoriesPage";
	}

	@PostMapping("/categoriesPage")
    public String categoriesSubmit(Spec spec, Model model) {
		if (spec.categories.size() <= 2) {
			spec = new Spec();
		    model.addAttribute("spec", spec);
		    model.addAttribute("categs", generateCategories());
			return "categoriesE";
		}
		spec.type = 2;
		spec.setAnswer(translateToBool(spec.categories));
		Storage.spec = spec;
		model.addAttribute("message", new Message());
		spec.setMinLength(assignMinimumLength(spec));
		model.addAttribute("length", spec.minLength);
        return "hiddenMessage";
    }
	
	private static LinkedList<String> duplicate(LinkedList<String> d) {
		LinkedList<String> answer = new LinkedList<>();
		for (String word: d) {
			answer.add(word);
		}
		return answer;
	}

	private int enteredMinimumLength(Spec spec) {
		// TODO Auto-generated method stub
		 String hiddenMessage = "12345";
	        for (int i = 0; i < 200; i++) {
	            for (int seed = 2; seed < 12; seed++) {
	                ProvidedWordsearch answer = new ProvidedWordsearch(0, 0, hiddenMessage, seed, duplicate(spec.getWordList()), seed);
	                boolean x = answer.run(true);
	                if (x) {
	                    return hiddenMessage.length();
	                }
	            }
	            hiddenMessage += "1";
	        }
		return -1;
	}
	
	private int assignMinimumLength(Spec spec) {
		 String hiddenMessage = "12345";
		for (int i = 0; i < 200; i++) {
            for (int seed = 2; seed < 12; seed++) {
            	NormalWordSearch w = new NormalWordSearch(spec.width, spec.height, hiddenMessage, seed, true, new boolean[]{false, false, true, true, true, false}, seed);
                boolean x = w.run(false);
                if (x) {
                    return hiddenMessage.length();
                }
            }
            hiddenMessage += "1";
        }
		return -1;
	}
	
	private LinkedList<String> generateCategories() {
		// TODO Auto-generated method stub
		LinkedList<String> answer = new LinkedList<>();
		answer.add("Animals");
		answer.add("Food");
		answer.add("Colors");
		answer.add("Body Parts");
		answer.add("Attire");
		answer.add("Household Items");
		return answer;
	}
	
	@PostMapping("/hiddenMessage")
    public String hiddenMessageSubmit(Message m, Model model) {
		if (Storage.spec.getMinLength() > m.getMessage().length()) {
			model.addAttribute("message", new Message());
			model.addAttribute("length", Storage.spec.minLength);
			return "hiddenMessageE";
		}
		Wordsearch a = generatePuzzle(Storage.spec, m.message);
		model.addAttribute("puzzle", a.finalAnswer);
		model.addAttribute("words", makeIntoList(a.seen));
		model.addAttribute("locs", a.locationsPlaced);
		model.addAttribute("title", Storage.spec.title);
		if (Math.max(Storage.spec.height, Storage.spec.width) > 10) {
			return "cleanAnswerB";
		}
        return "cleanAnswer";
    }
	
	public static String[] makeIntoList(LinkedList<String> seen) {
		// TODO Auto-generated method stub
		String[] answer = new String[seen.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = seen.removeFirst();
		}
		return answer;
	}

	public static Wordsearch generatePuzzle(Spec spec, String message) {
		Random rand = new Random();
		int seed = rand.nextInt();
		if (spec.type == 3) {
			ProvidedWordsearch w;
				for (int i = 0; i < 10000; i++) {
	            w = new ProvidedWordsearch(0, 0, message, seed, duplicate(spec.getWordList()), i);
	            if (w.run(true)) {
	                return w;
	            }
	            seed += 1;
	        }
		}
		// TODO Auto-generated method stub
		else {
			NormalWordSearch w;
			for (int i = 0; i < 10000; i++) {
	            w = new NormalWordSearch(spec.width, spec.height, message, seed, true, spec.answer, i);
	            if (w.run(true)) {
	                return w;
	            }
	            seed += 1;
	        }
		}
		return null;
	}
	
	private static boolean[] randomBool() {
		Random rand = new Random();
		boolean[] answer = new boolean[6];
		int times = 4;
		while (count(answer) >= 3 && times < 3) { 
			for (int i = 0; i < 6; i++) {
				answer[i] = rand.nextBoolean() || rand.nextBoolean();
				}
			times += 1;
			}
		if (times > 3) {
			for (int i = 0; i < 6; i++) {
				answer[i] = true;
			}
		}
		return answer;
	}
	
	private static boolean[] translateToBool(LinkedList<String> categories) {
		boolean[] answer = new boolean[6];

		// TODO Auto-generated method stub
		if (categories.contains("Animals")) {
			answer[0] = true;
		}
		if (categories.contains("Colors")) {
			answer[1] = true;
		}
		if (categories.contains("Body Parts")) {
			answer[2] = true;
		}
		if (categories.contains("Food")) {
			answer[3] = true;
		}
		if (categories.contains("Attire")) {
			answer[4] = true;
		}
		if (categories.contains("Household Items")) {
			answer[5] = true;
		}
		return answer;
	}

	private static int count(boolean[] answer) {
		// TODO Auto-generated method stub
		int count = 0;
		for (int i = 0; i < answer.length; i++) {
			if (answer[i]) {
				count += 1;
			}			
		}
		return count;
	}

	private LinkedList<String> string_to_list(String words) {
        LinkedList<String> answer = new LinkedList<>();
        String curr = "";
        words =  words.toLowerCase();
        for (int i = 0; i < words.length(); i++) {
            char a = words.charAt(i);
            int val = Character.getNumericValue(a);
            if ((val < 10 || val > 35) && a != ' ') {
                continue;
            }
            if (a == ' ') {
                if (curr != "") {
                    answer.addLast(curr);
                    curr = "";
                }
            } else {
                curr += a;
            }
        }
        answer.addLast(curr);
        return answer;
	}
}



