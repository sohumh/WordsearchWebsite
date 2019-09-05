package com.boot.specifications.alg;

import java.util.*;

public class Word {
    static LinkedList<String> animals_three;
    static LinkedList<String> animals_four;
    static LinkedList<String> animals_five;
    static LinkedList<String> animals_six;
    static LinkedList<String> animals_seven;
    static LinkedList<String> animals_eight;
    static LinkedList<String> animals_nine;
    static LinkedList<String> animals_ten;
    static LinkedList<String> animals_eleven;
    static LinkedList<String> animals_twelve;

    static LinkedList<String> household_items_three;
    static LinkedList<String> household_items_four;
    static LinkedList<String> household_items_five;
    static LinkedList<String> household_items_six;
    static LinkedList<String> household_items_seven;
    static LinkedList<String> household_items_eight;
    static LinkedList<String> household_items_nine;
    static LinkedList<String> household_items_ten;
    static LinkedList<String> household_items_eleven;
    static LinkedList<String> household_items_twelve;

    static LinkedList<String> food_three;
    static LinkedList<String> food_four;
    static LinkedList<String> food_five;
    static LinkedList<String> food_six;
    static LinkedList<String> food_seven;
    static LinkedList<String> food_eight;
    static LinkedList<String> food_nine;
    static LinkedList<String> food_ten;
    static LinkedList<String> food_eleven;
    static LinkedList<String> food_twelve;

    static LinkedList<String> body_parts_three;
    static LinkedList<String> body_parts_four;
    static LinkedList<String> body_parts_five;
    static LinkedList<String> body_parts_six;
    static LinkedList<String> body_parts_seven;
    static LinkedList<String> body_parts_eight;
    static LinkedList<String> body_parts_nine;
    static LinkedList<String> body_parts_ten;

    static LinkedList<String> colors_three;
    static LinkedList<String> colors_four;
    static LinkedList<String> colors_five;
    static LinkedList<String> colors_six;
    static LinkedList<String> colors_seven;
    static LinkedList<String> colors_eight;
    static LinkedList<String> colors_nine;
    static LinkedList<String> colors_ten;
    static LinkedList<String> colors_eleven;
    static LinkedList<String> colors_twelve;

    static LinkedList<String> attire_three;
    static LinkedList<String> attire_four;
    static LinkedList<String> attire_five;
    static LinkedList<String> attire_six;
    static LinkedList<String> attire_seven;
    static LinkedList<String> attire_eight;
    static LinkedList<String> attire_nine;
    static LinkedList<String> attire_ten;
    static LinkedList<String> attire_eleven;
    static LinkedList<String> attire_twelve;

    public int category;
    public boolean has_words;
    public Random rand;
    public boolean[] categs;

    public Word(boolean[] categories, Random rand) {
        categs = categories;
        has_words = false;
        this.rand = rand;
        if (categories[0]) {
            animals();
            has_words = true;
        }
        if (categories[1]) {
            colors();
            has_words = true;
        }
        if (categories[2]) {
            body_parts();
            has_words = true;
        }
        if (categories[3]) {
            food();
            has_words = true;
        }
        if (categories[4]) {
            attire();
            has_words = true;
        }
        if (categories[5]) {
            household_items();
            has_words = true;
        }
        category = 0;
        while (!categs[category]) {
            category++;
        }
    }

    public HashMap<Character, HashMap<Integer, LinkedList<String>>> generateLetterMap() {
        HashMap<Character, HashMap<Integer, LinkedList<String>>> answer = new HashMap<>();
        if (categs[0]) {
            for (String word : animals_twelve) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(12)) {
                        answer.get(a).put(12, new LinkedList<>());
                    }
                    answer.get(a).get(12).add(word);
                }
            }
            for (String word : animals_eleven) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(11)) {
                        answer.get(a).put(11, new LinkedList<>());
                    }
                    answer.get(a).get(11).add(word);
                }
            }
            for (String word : animals_ten) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(10)) {
                        answer.get(a).put(10, new LinkedList<>());
                    }
                    answer.get(a).get(10).add(word);
                }
            }
            for (String word : animals_nine) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(9)) {
                        answer.get(a).put(9, new LinkedList<>());
                    }
                    answer.get(a).get(9).add(word);
                }
            }
            for (String word : animals_eight) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(8)) {
                        answer.get(a).put(8, new LinkedList<>());
                    }
                    answer.get(a).get(8).add(word);
                }
            }
            for (String word : animals_seven) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(7)) {
                        answer.get(a).put(7, new LinkedList<>());
                    }
                    answer.get(a).get(7).add(word);
                }
            }
            for (String word : animals_six) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(6)) {
                        answer.get(a).put(6, new LinkedList<>());
                    }
                    answer.get(a).get(6).add(word);
                }
            }
            for (String word : animals_five) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(5)) {
                        answer.get(a).put(5, new LinkedList<>());
                    }
                    answer.get(a).get(5).add(word);
                }
            }
            for (String word : animals_four) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(4)) {
                        answer.get(a).put(4, new LinkedList<>());
                    }
                    answer.get(a).get(4).add(word);
                }
            }
            for (String word : animals_three) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(3)) {
                        answer.get(a).put(3, new LinkedList<>());
                    }
                    answer.get(a).get(3).add(word);
                }
            }
        }

        if (categs[1]) {
            for (String word : colors_ten) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(10)) {
                        answer.get(a).put(10, new LinkedList<>());
                    }
                    answer.get(a).get(10).add(word);
                }
            }
            for (String word : colors_nine) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(9)) {
                        answer.get(a).put(9, new LinkedList<>());
                    }
                    answer.get(a).get(9).add(word);
                }
            }
            for (String word : colors_eight) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(8)) {
                        answer.get(a).put(8, new LinkedList<>());
                    }
                    answer.get(a).get(8).add(word);
                }
            }
            for (String word : colors_seven) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(7)) {
                        answer.get(a).put(7, new LinkedList<>());
                    }
                    answer.get(a).get(7).add(word);
                }
            }
            for (String word : colors_six) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(6)) {
                        answer.get(a).put(6, new LinkedList<>());
                    }
                    answer.get(a).get(6).add(word);
                }
            }
            for (String word : colors_five) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(5)) {
                        answer.get(a).put(5, new LinkedList<>());
                    }
                    answer.get(a).get(5).add(word);
                }
            }
            for (String word : colors_four) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(4)) {
                        answer.get(a).put(4, new LinkedList<>());
                    }
                    answer.get(a).get(4).add(word);
                }
            }
            for (String word : colors_three) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(3)) {
                        answer.get(a).put(3, new LinkedList<>());
                    }
                    answer.get(a).get(3).add(word);
                }
            }
        }

        if (categs[2]) {
            for (String word : body_parts_ten) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(10)) {
                        answer.get(a).put(10, new LinkedList<>());
                    }
                    answer.get(a).get(10).add(word);
                }
            }
            for (String word : body_parts_nine) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(9)) {
                        answer.get(a).put(9, new LinkedList<>());
                    }
                    answer.get(a).get(9).add(word);
                }
            }
            for (String word : body_parts_eight) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(8)) {
                        answer.get(a).put(8, new LinkedList<>());
                    }
                    answer.get(a).get(8).add(word);
                }
            }
            for (String word : body_parts_seven) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(7)) {
                        answer.get(a).put(7, new LinkedList<>());
                    }
                    answer.get(a).get(7).add(word);
                }
            }
            for (String word : body_parts_six) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(6)) {
                        answer.get(a).put(6, new LinkedList<>());
                    }
                    answer.get(a).get(6).add(word);
                }
            }
            for (String word : body_parts_five) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(5)) {
                        answer.get(a).put(5, new LinkedList<>());
                    }
                    answer.get(a).get(5).add(word);
                }
            }
            for (String word : body_parts_four) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(4)) {
                        answer.get(a).put(4, new LinkedList<>());
                    }
                    answer.get(a).get(4).add(word);
                }
            }
            for (String word : body_parts_three) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(3)) {
                        answer.get(a).put(3, new LinkedList<>());
                    }
                    answer.get(a).get(3).add(word);
                }
            }
        }

        if (categs[3]) {
            for (String word : food_ten) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(10)) {
                        answer.get(a).put(10, new LinkedList<>());
                    }
                    answer.get(a).get(10).add(word);
                }
            }
            for (String word : food_nine) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(9)) {
                        answer.get(a).put(9, new LinkedList<>());
                    }
                    answer.get(a).get(9).add(word);
                }
            }
            for (String word : food_eight) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(8)) {
                        answer.get(a).put(8, new LinkedList<>());
                    }
                    answer.get(a).get(8).add(word);
                }
            }
            for (String word : food_seven) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(7)) {
                        answer.get(a).put(7, new LinkedList<>());
                    }
                    answer.get(a).get(7).add(word);
                }
            }
            for (String word : food_six) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(6)) {
                        answer.get(a).put(6, new LinkedList<>());
                    }
                    answer.get(a).get(6).add(word);
                }
            }
            for (String word : food_five) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(5)) {
                        answer.get(a).put(5, new LinkedList<>());
                    }
                    answer.get(a).get(5).add(word);
                }
            }
            for (String word : food_four) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(4)) {
                        answer.get(a).put(4, new LinkedList<>());
                    }
                    answer.get(a).get(4).add(word);
                }
            }
            for (String word : food_three) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(3)) {
                        answer.get(a).put(3, new LinkedList<>());
                    }
                    answer.get(a).get(3).add(word);
                }
            }
        }

        if (categs[4]) {
            for (String word : attire_ten) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(10)) {
                        answer.get(a).put(10, new LinkedList<>());
                    }
                    answer.get(a).get(10).add(word);
                }
            }
            for (String word : attire_nine) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(9)) {
                        answer.get(a).put(9, new LinkedList<>());
                    }
                    answer.get(a).get(9).add(word);
                }
            }
            for (String word : attire_eight) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(8)) {
                        answer.get(a).put(8, new LinkedList<>());
                    }
                    answer.get(a).get(8).add(word);
                }
            }
            for (String word : attire_seven) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(7)) {
                        answer.get(a).put(7, new LinkedList<>());
                    }
                    answer.get(a).get(7).add(word);
                }
            }
            for (String word : attire_six) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(6)) {
                        answer.get(a).put(6, new LinkedList<>());
                    }
                    answer.get(a).get(6).add(word);
                }
            }
            for (String word : attire_five) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(5)) {
                        answer.get(a).put(5, new LinkedList<>());
                    }
                    answer.get(a).get(5).add(word);
                }
            }
            for (String word : attire_four) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(4)) {
                        answer.get(a).put(4, new LinkedList<>());
                    }
                    answer.get(a).get(4).add(word);
                }
            }
            for (String word : attire_three) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(3)) {
                        answer.get(a).put(3, new LinkedList<>());
                    }
                    answer.get(a).get(3).add(word);
                }
            }
        }

        if (categs[5]) {
            for (String word : household_items_ten) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(10)) {
                        answer.get(a).put(10, new LinkedList<>());
                    }
                    answer.get(a).get(10).add(word);
                }
            }
            for (String word : household_items_nine) {
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(9)) {
                        answer.get(a).put(9, new LinkedList<>());
                    }
                    answer.get(a).get(9).add(word);
                }
            }
            for (String word : household_items_eight) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(8)) {
                        answer.get(a).put(8, new LinkedList<>());
                    }
                    answer.get(a).get(8).add(word);
                }
            }
            for (String word : household_items_seven) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(7)) {
                        answer.get(a).put(7, new LinkedList<>());
                    }
                    answer.get(a).get(7).add(word);
                }
            }
            for (String word : household_items_six) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(6)) {
                        answer.get(a).put(6, new LinkedList<>());
                    }
                    answer.get(a).get(6).add(word);
                }
            }
            for (String word : household_items_five) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(5)) {
                        answer.get(a).put(5, new LinkedList<>());
                    }
                    answer.get(a).get(5).add(word);
                }
            }
            for (String word : household_items_four) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(4)) {
                        answer.get(a).put(4, new LinkedList<>());
                    }
                    answer.get(a).get(4).add(word);
                }
            }
            for (String word : household_items_three) {
                HashSet<Integer> seen = new HashSet<>();

                for (int i = 0; i < word.length(); i++) {
                    char a = word.charAt(i);
                    if (seen.contains(a)) {
                        continue;
                    }
                    if (!answer.containsKey(a)) {
                        answer.put(a, new HashMap<>());
                    }
                    if (!answer.get(a).containsKey(3)) {
                        answer.get(a).put(3, new LinkedList<>());
                    }
                    answer.get(a).get(3).add(word);
                }
            }
        }

        return answer;

    }


    public String getWord(int length, boolean forwards) {
        //Returns a RANDOM word of the designated length, and removes that word from the linkedlist
        LinkedList<String> list;

        if (category == 0) {
            if (length == 3) {
                list = animals_three;
            } else if (length == 4) {
                list = animals_four;
            } else if (length == 5) {
                list = animals_five;
            } else if (length == 6) {
                list = animals_six;
            } else if (length == 7) {
                list = animals_seven;
            } else if (length == 8) {
                list = animals_eight;
            } else if (length == 9) {
                list = animals_nine;
            } else if (length == 10) {
                list = animals_ten;
            } else if (length == 11) {
                list = animals_eleven;
            } else if (length == 12) {
                list = animals_twelve;
            } else {
                return null;
            }
        } else if (category == 1) {
            if (length == 3) {
                list = colors_three;
            } else if (length == 4) {
                list = colors_four;
            } else if (length == 5) {
                list = colors_five;
            } else if (length == 6) {
                list = colors_six;
            } else if (length == 7) {
                list = colors_seven;
            } else if (length == 8) {
                list = colors_eight;
            } else if (length == 9) {
                list = colors_nine;
            } else if (length == 10) {
                list = colors_ten;
            } else if (length == 11) {
                list = colors_eleven;
            } else if (length == 12) {
                list = colors_twelve;
            } else {
                return null;
            }
        } else if (category == 4) {
            if (length == 3) {
                list = attire_three;
            } else if (length == 4) {
                list = attire_four;
            } else if (length == 5) {
                list = attire_five;
            } else if (length == 6) {
                list = attire_six;
            } else if (length == 7) {
                list = attire_seven;
            } else if (length == 8) {
                list = attire_eight;
            } else if (length == 9) {
                list = attire_nine;
            } else if (length == 10) {
                list = attire_ten;
            } else if (length == 11) {
                list = attire_eleven;
            } else if (length == 12) {
                list = attire_twelve;
            } else {
                return null;
            }
        } else if (category == 3) {
            if (length == 3) {
                list = food_three;
            } else if (length == 4) {
                list = food_four;
            } else if (length == 5) {
                list = food_five;
            } else if (length == 6) {
                list = food_six;
            } else if (length == 7) {
                list = food_seven;
            } else if (length == 8) {
                list = food_eight;
            } else if (length == 9) {
                list = food_nine;
            } else if (length == 10) {
                list = food_ten;
            } else if (length == 11) {
                list = food_eleven;
            } else if (length == 12) {
                list = food_twelve;
            } else {
                return null;
            }
        } else if (category == 5) {
            if (length == 3) {
                list = household_items_three;
            } else if (length == 4) {
                list = household_items_four;
            } else if (length == 5) {
                list = household_items_five;
            } else if (length == 6) {
                list = household_items_six;
            } else if (length == 7) {
                list = household_items_seven;
            } else if (length == 8) {
                list = household_items_eight;
            } else if (length == 9) {
                list = household_items_nine;
            } else if (length == 10) {
                list = household_items_ten;
            } else if (length == 11) {
                list = household_items_eleven;
            } else if (length == 12) {
                list = household_items_twelve;
            } else {
                return null;
            }
        } else if (category == 2) {
            if (length == 3) {
                list = body_parts_three;
            } else if (length == 4) {
                list = body_parts_four;
            } else if (length == 5) {
                list = body_parts_five;
            } else if (length == 6) {
                list = body_parts_six;
            } else if (length == 7) {
                list = body_parts_seven;
            } else if (length == 8) {
                list = body_parts_eight;
            } else if (length == 9) {
                list = body_parts_nine;
            } else if (length == 10) {
                list = body_parts_ten;
            } else {
                return null;
            }
        } else {
            return null;
        }

        //gives a decimal value between 0 and 1 //length of list //changes it to a integer
        category = (category + 1) % 6;
        while (!categs[category]) {
            category = (category + 1) % 6;
        }
        if (list.size() == 0) {
            return getWord(length, forwards);
        }
        int randomNum = rand.nextInt(list.size());
        String finalAnswer = list.get(randomNum);
        if (has_words) {
            NormalWordSearch.removeWord(finalAnswer);
        }
        list.remove(randomNum);


        if (!forwards) {
            return reverse(finalAnswer);
        } else {
            return finalAnswer;
        }


        //Now I have a random number of the length of the list, so corresponding with the random number, I choose the string
        // Then, if forwards == false, use the reverse function, else return string
        // Then, return string
        //index is specified AT RANDOM
    }


    public static String reverse(String s) {
        String new_word = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            char a = s.charAt(i);
            new_word = new_word + a;
        }
        return new_word;
    }

    public static void animals() {
        animals_three = new LinkedList<>();
        animals_three.addFirst("elk");
        animals_three.addFirst("emu");
        animals_three.addFirst("cat");
        animals_three.addFirst("rat");
        animals_three.addFirst("pig");
        animals_three.addFirst("bee");
        animals_three.addFirst("ant");
        animals_three.addFirst("cow");
        animals_three.addFirst("eel");
        animals_three.addFirst("fox");
        animals_three.addFirst("fly");
        animals_three.addFirst("hen");
        animals_three.addFirst("hog");
        animals_three.addFirst("owl");
        animals_three.addFirst("yak");
        animals_three.addFirst("sow");
        animals_three.addFirst("jay");
        animals_three.addFirst("bat");
        animals_three.addFirst("doe");
        animals_three.addFirst("ram");
        animals_four = new LinkedList<>();
        animals_four.addFirst("frog");
        animals_four.addFirst("bear");
        animals_four.addFirst("duck");
        animals_four.addFirst("goat");
        animals_four.addFirst("lion");
        animals_four.addFirst("mole");
        animals_four.addFirst("crab");
        animals_four.addFirst("hare");
        animals_four.addFirst("toad");
        animals_four.addFirst("wolf");
        animals_four.addFirst("lynx");
        animals_four.addFirst("lamb");
        animals_four.addFirst("newt");
        animals_four.addFirst("seal");
        animals_four.addFirst("joey");
        animals_four.addFirst("calf");
        animals_four.addFirst("boar");
        animals_four.addFirst("colt");
        animals_four.addFirst("bull");
        animals_four.addFirst("deer");
        animals_five = new LinkedList<>();
        animals_five.addFirst("horse");
        animals_five.addFirst("tiger");
        animals_five.addFirst("snake");
        animals_five.addFirst("zebra");
        animals_five.addFirst("mouse");
        animals_five.addFirst("sheep");
        animals_five.addFirst("whale");
        animals_five.addFirst("panda");
        animals_five.addFirst("shark");
        animals_five.addFirst("moose");
        animals_five.addFirst("bison");
        animals_five.addFirst("camel");
        animals_five.addFirst("chimp");
        animals_five.addFirst("finch");
        animals_five.addFirst("hippo");
        animals_five.addFirst("hyena");
        animals_five.addFirst("koala");
        animals_five.addFirst("lemur");
        animals_five.addFirst("llama");
        animals_five.addFirst("otter");
        animals_five.addFirst("puppy");
        animals_five.addFirst("rhino");
        animals_five.addFirst("sloth");
        animals_six = new LinkedList<>();
        animals_six.addFirst("spider");
        animals_six.addFirst("walrus");
        animals_six.addFirst("monkey");
        animals_six.addFirst("turtle");
        animals_six.addFirst("wombat");
        animals_six.addFirst("parrot");
        animals_six.addFirst("condor");
        animals_six.addFirst("salmon");
        animals_six.addFirst("beaver");
        animals_six.addFirst("rabbit");
        animals_six.addFirst("alpaca");
        animals_six.addFirst("falcon");
        animals_six.addFirst("cougar");
        animals_six.addFirst("jaguar");
        animals_six.addFirst("pigeon");
        animals_six.addFirst("puffin");
        animals_six.addFirst("osprey");
        animals_six.addFirst("toucan");
        animals_six.addFirst("canary");
        animals_six.addFirst("bobcat");
        animals_seven = new LinkedList<>();
        animals_seven.addFirst("giraffe");
        animals_seven.addFirst("meerkat");
        animals_seven.addFirst("cheetah");
        animals_seven.addFirst("gorilla");
        animals_seven.addFirst("warthog");
        animals_seven.addFirst("raccoon");
        animals_seven.addFirst("octopus");
        animals_seven.addFirst("peacock");
        animals_seven.addFirst("hamster");
        animals_seven.addFirst("leopard");
        animals_seven.addFirst("gazelle");
        animals_seven.addFirst("manatee");
        animals_seven.addFirst("dolphin");
        animals_seven.addFirst("narwhal");
        animals_seven.addFirst("penguin");
        animals_seven.addFirst("buffalo");
        animals_seven.addFirst("vulture");
        animals_seven.addFirst("phoenix");
        animals_eight = new LinkedList<>();
        animals_eight.addFirst("elephant");
        animals_eight.addFirst("chipmunk");
        animals_eight.addFirst("anteater");
        animals_eight.addFirst("flamingo");
        animals_eight.addFirst("aardvark");
        animals_eight.addFirst("seahorse");
        animals_eight.addFirst("tortoise");
        animals_eight.addFirst("mongoose");
        animals_eight.addFirst("blowfish");
        animals_eight.addFirst("starfish");
        animals_eight.addFirst("squirrel");
        animals_eight.addFirst("platypus");
        animals_eight.addFirst("reindeer");
        animals_eight.addFirst("antelope");
        animals_eight.addFirst("capybara");
        animals_eight.addFirst("pheasant");
        animals_nine = new LinkedList<>();
        animals_nine.addFirst("butterfly");
        animals_nine.addFirst("orangutan");
        animals_nine.addFirst("porcupine");
        animals_nine.addFirst("chameleon");
        animals_nine.addFirst("alligator");
        animals_nine.addFirst("centipede");
        animals_nine.addFirst("chihuahua");
        animals_nine.addFirst("crocodile");
        animals_nine.addFirst("dalmation");
        animals_nine.addFirst("millipede");
        animals_nine.addFirst("angelfish");
        animals_nine.addFirst("jellyfish");
        animals_nine.addFirst("armadillo");
        animals_nine.addFirst("orangutan");
        animals_ten = new LinkedList<>();
        animals_ten.addFirst("woodpecker");
        animals_ten.addFirst("roadrunner");
        animals_ten.addFirst("salamander");
        animals_ten.addFirst("chinchilla");
        animals_eleven = new LinkedList<>();
        animals_eleven.addFirst("caterpillar");
        animals_eleven.addFirst("grasshopper");
        animals_eleven.addFirst("rattlesnake");
        animals_eleven.addFirst("hummingbird");
        animals_eleven.addFirst("mockingbird");
        animals_eleven.addFirst("nightingale");
        animals_eleven.addFirst("grizzlybear");
        animals_eleven.addFirst("snowleopard");
        animals_twelve = new LinkedList<>();
        animals_twelve.addFirst("hippopotamus");
    }

    public static void food() {
        food_three = new LinkedList<>();
        food_three.addFirst("fig");
        food_three.addFirst("pea");
        food_three.addFirst("yam");
        food_three.addFirst("pie");
        food_three.addFirst("jam");
        food_three.addFirst("egg");
        food_three.addFirst("ham");
        food_three.addFirst("gum");
        food_three.addFirst("oat");
        food_three.addFirst("dip");
        food_three.addFirst("ice");
        food_three.addFirst("tea");
        food_three.addFirst("soy");
        food_three.addFirst("bun");
        food_three.addFirst("rye");
        food_three.addFirst("nut");
        food_three.addFirst("ale");
        food_three.addFirst("fry");
        food_four = new LinkedList<>();
        food_four.addFirst("bean");
        food_four.addFirst("beet");
        food_four.addFirst("corn");
        food_four.addFirst("date");
        food_four.addFirst("kale");
        food_four.addFirst("kiwi");
        food_four.addFirst("lime");
        food_four.addFirst("pear");
        food_four.addFirst("plum");
        food_four.addFirst("milk");
        food_four.addFirst("rice");
        food_four.addFirst("soup");
        food_four.addFirst("beef");
        food_four.addFirst("pork");
        food_four.addFirst("cake");
        food_four.addFirst("meat");
        food_four.addFirst("beet");
        food_four.addFirst("ziti");
        food_four.addFirst("veal");
        food_four.addFirst("pita");
        food_four.addFirst("brie");
        food_five = new LinkedList<>();
        food_five.addFirst("bread");
        food_five.addFirst("apple");
        food_five.addFirst("pasta");
        food_five.addFirst("salad");
        food_five.addFirst("pizza");
        food_five.addFirst("sushi");
        food_five.addFirst("steak");
        food_five.addFirst("toast");
        food_five.addFirst("jelly");
        food_five.addFirst("cream");
        food_five.addFirst("peach");
        food_five.addFirst("grape");
        food_five.addFirst("pesto");
        food_five.addFirst("bagel");
        food_five.addFirst("donut");
        food_five.addFirst("ranch");
        food_five.addFirst("olive");
        food_five.addFirst("onion");
        food_five.addFirst("curry");
        food_five.addFirst("pecan");
        food_five.addFirst("syrup");
        food_five.addFirst("jello");
        food_five.addFirst("sauce");
        food_five.addFirst("bacon");
        food_five.addFirst("chili");
        food_five.addFirst("honey");
        food_five.addFirst("basil");
        food_six = new LinkedList<>();
        food_six.addFirst("orange");
        food_six.addFirst("almond");
        food_six.addFirst("banana");
        food_six.addFirst("carrot");
        food_six.addFirst("cashew");
        food_six.addFirst("celery");
        food_six.addFirst("cherry");
        food_six.addFirst("garlic");
        food_six.addFirst("raisin");
        food_six.addFirst("lychee");
        food_six.addFirst("oyster");
        food_six.addFirst("papaya");
        food_six.addFirst("peanut");
        food_six.addFirst("pepper");
        food_six.addFirst("potato");
        food_six.addFirst("radish");
        food_six.addFirst("squash");
        food_six.addFirst("tomato");
        food_six.addFirst("turnip");
        food_six.addFirst("citrus");
        food_seven = new LinkedList<>();
        food_seven.addFirst("biscuit");
        food_seven.addFirst("burrito");
        food_seven.addFirst("chowder");
        food_seven.addFirst("chicken");
        food_seven.addFirst("cupcake");
        food_seven.addFirst("ketchup");
        food_seven.addFirst("lettuce");
        food_seven.addFirst("lobster");
        food_seven.addFirst("mustard");
        food_seven.addFirst("pancake");
        food_seven.addFirst("sausage");
        food_seven.addFirst("popcorn");
        food_seven.addFirst("apricot");
        food_seven.addFirst("avacado");
        food_seven.addFirst("cabbage");
        food_seven.addFirst("coconut");
        food_seven.addFirst("custard");
        food_seven.addFirst("edamame");
        food_seven.addFirst("pumpkin");
        food_seven.addFirst("rhubarb");
        food_seven.addFirst("parsnip");
        food_seven.addFirst("parsley");
        food_eight = new LinkedList<>();
        food_eight.addFirst("cucumber");
        food_eight.addFirst("lollipop");
        food_eight.addFirst("broccoli");
        food_eight.addFirst("mushroom");
        food_eight.addFirst("porkchop");
        food_eight.addFirst("sandwich");
        food_eight.addFirst("tortilla");
        food_eight.addFirst("chestnut");
        food_eight.addFirst("chickpea");
        food_eight.addFirst("eggplant");
        food_eight.addFirst("garbanzo");
        food_eight.addFirst("hazelnut");
        food_eight.addFirst("honeydew");
        food_eight.addFirst("rutabaga");
        food_eight.addFirst("scallion");
        food_eight.addFirst("zucchini");
        food_eight.addFirst("lemonade");
        food_nine = new LinkedList<>();
        food_nine.addFirst("blueberry");
        food_nine.addFirst("hamburger");
        food_nine.addFirst("milkshake");
        food_nine.addFirst("nectarine");
        food_nine.addFirst("raspberry");
        food_nine.addFirst("spaghetti");
        food_nine.addFirst("artichoke");
        food_nine.addFirst("asparagus");
        food_nine.addFirst("cranberry");
        food_nine.addFirst("jackfruit");
        food_nine.addFirst("persimmon");
        food_nine.addFirst("tangerine");
        food_nine.addFirst("nectarine");
        food_nine.addFirst("pistachio");
        food_ten = new LinkedList<>();
        food_ten.addFirst("strawberry");
        food_ten.addFirst("blackberry");
        food_ten.addFirst("clementine");
        food_ten.addFirst("cantaloupe");
        food_ten.addFirst("watermelon");
        food_eleven = new LinkedList<>();
        food_eleven.addFirst("cauliflower");
        food_eleven.addFirst("pomegranate");
        food_eleven.addFirst("huckleberry");
        food_twelve = new LinkedList<>();
        food_twelve.addFirst("passionfruit");
    }

    public static void household_items() {
        household_items_three = new LinkedList<>();
        household_items_three.addFirst("bed");
        household_items_three.addFirst("pan");
        household_items_three.addFirst("fan");
        household_items_three.addFirst("pot");
        household_items_three.addFirst("can");
        household_items_three.addFirst("cup");
        household_items_three.addFirst("mug");
        household_items_three.addFirst("tin");
        household_items_three.addFirst("bin");
        household_items_three.addFirst("bag");
        household_items_three.addFirst("mat");
        household_items_three.addFirst("mop");
        household_items_three.addFirst("pen");
        household_items_three.addFirst("toy");
        household_items_three.addFirst("ink");
        household_items_three.addFirst("box");
        household_items_three.addFirst("pen");
        household_items_three.addFirst("rug");
        household_items_four = new LinkedList<>();
        household_items_four.addFirst("sofa");
        household_items_four.addFirst("lamp");
        household_items_four.addFirst("door");
        household_items_four.addFirst("knob");
        household_items_four.addFirst("rack");
        household_items_four.addFirst("sink");
        household_items_four.addFirst("desk");
        household_items_four.addFirst("suit");
        household_items_four.addFirst("fork");
        household_items_four.addFirst("bath");
        household_items_four.addFirst("dish");
        household_items_four.addFirst("bike");
        household_items_four.addFirst("clip");
        household_items_four.addFirst("comb");
        household_items_four.addFirst("book");
        household_items_four.addFirst("wall");
        household_items_four.addFirst("room");
        household_items_four.addFirst("iron");
        household_items_four.addFirst("oven");
        household_items_four.addFirst("shoe");
        household_items_four.addFirst("vase");
        household_items_five = new LinkedList<>();
        household_items_five.addFirst("clock");
        household_items_five.addFirst("drape");
        household_items_five.addFirst("blind");
        household_items_five.addFirst("broom");
        household_items_five.addFirst("brush");
        household_items_five.addFirst("chair");
        household_items_five.addFirst("china");
        household_items_five.addFirst("table");
        household_items_five.addFirst("couch");
        household_items_five.addFirst("towel");
        household_items_five.addFirst("drill");
        household_items_five.addFirst("dryer");
        household_items_five.addFirst("light");
        household_items_five.addFirst("linen");
        household_items_five.addFirst("phone");
        household_items_five.addFirst("knife");
        household_items_five.addFirst("spoon");
        household_items_five.addFirst("paper");
        household_items_five.addFirst("piano");
        household_items_five.addFirst("plant");
        household_items_five.addFirst("plier");
        household_items_five.addFirst("radio");
        household_items_five.addFirst("screw");
        household_items_five.addFirst("sheet");
        household_items_five.addFirst("shelf");
        household_items_five.addFirst("stove");
        household_items_five.addFirst("razor");
        household_items_six = new LinkedList<>();
        household_items_six.addFirst("napkin");
        household_items_six.addFirst("binder");
        household_items_six.addFirst("bucket");
        household_items_six.addFirst("candle");
        household_items_six.addFirst("fridge");
        household_items_six.addFirst("carpet");
        household_items_six.addFirst("hammer");
        household_items_six.addFirst("heater");
        household_items_six.addFirst("marker");
        household_items_six.addFirst("magnet");
        household_items_six.addFirst("pencil");
        household_items_six.addFirst("pillow");
        household_items_six.addFirst("toilet");
        household_items_six.addFirst("lotion");
        household_items_six.addFirst("saucer");
        household_items_six.addFirst("tissue");
        household_items_six.addFirst("vacuum");
        household_items_six.addFirst("washer");
        household_items_six.addFirst("camera");
        household_items_six.addFirst("guitar");
        household_items_six.addFirst("laptop");
        household_items_six.addFirst("stereo");
        household_items_seven = new LinkedList<>();
        household_items_seven.addFirst("armoire");
        household_items_seven.addFirst("bedding");
        household_items_seven.addFirst("blanket");
        household_items_seven.addFirst("curtain");
        household_items_seven.addFirst("cushion");
        household_items_seven.addFirst("dresser");
        household_items_seven.addFirst("cabinet");
        household_items_seven.addFirst("flowers");
        household_items_seven.addFirst("furnace");
        household_items_seven.addFirst("jewelry");
        household_items_seven.addFirst("postits");
        household_items_seven.addFirst("picture");
        household_items_seven.addFirst("pitcher");
        household_items_seven.addFirst("speaker");
        household_items_seven.addFirst("toaster");
        household_items_seven.addFirst("blender");
        household_items_seven.addFirst("garbage");
        household_items_seven.addFirst("stapler");
        household_items_eight = new LinkedList<>();
        household_items_eight.addFirst("backpack");
        household_items_eight.addFirst("bookcase");
        household_items_eight.addFirst("calendar");
        household_items_eight.addFirst("computer");
        household_items_eight.addFirst("figurine");
        household_items_eight.addFirst("medicine");
        household_items_eight.addFirst("painting");
        household_items_eight.addFirst("radiator");
        household_items_eight.addFirst("scissors");
        household_items_eight.addFirst("suitcase");
        household_items_eight.addFirst("supplies");
        household_items_eight.addFirst("doorbell");
        household_items_eight.addFirst("keyboard");
        household_items_eight.addFirst("trashcan");
        household_items_nine = new LinkedList<>();
        household_items_nine.addFirst("bedspread");
        household_items_nine.addFirst("generator");
        household_items_nine.addFirst("comforter");
        household_items_nine.addFirst("container");
        household_items_nine.addFirst("notepaper");
        household_items_nine.addFirst("telephone");
        household_items_nine.addFirst("microwave");
        household_items_nine.addFirst("lightbulb");
        household_items_nine.addFirst("cellphone");
        household_items_nine.addFirst("canopener");
        household_items_nine.addFirst("sharpener");
        household_items_nine.addFirst("lawnmower");
        household_items_nine.addFirst("mousetrap");
        household_items_ten = new LinkedList<>();
        household_items_ten.addFirst("toothpaste");
        household_items_ten.addFirst("toothbrush");
        household_items_ten.addFirst("tablecloth");
        household_items_ten.addFirst("photograph");
        household_items_ten.addFirst("houseplant");
        household_items_ten.addFirst("dishwasher");
        household_items_ten.addFirst("calculator");
        household_items_ten.addFirst("houseplant");
        household_items_ten.addFirst("humidifier");
        household_items_ten.addFirst("headphones");
        household_items_ten.addFirst("leafblower");
        household_items_ten.addFirst("television");
        household_items_eleven = new LinkedList<>();
        household_items_eleven.addFirst("lightswitch");
        household_items_eleven.addFirst("electricity");
        household_items_eleven.addFirst("toiletpaper");
        household_items_twelve = new LinkedList<>();
    }

    public static void attire() {
        attire_three = new LinkedList<>();
        attire_three.addFirst("tie");
        attire_three.addFirst("hat");
        attire_three.addFirst("cap");
        attire_three.addFirst("bib");
        attire_three.addFirst("bra");
        attire_three.addFirst("wig");
        attire_four = new LinkedList<>();
        attire_four.addFirst("pant");
        attire_four.addFirst("shoe");
        attire_four.addFirst("sock");
        attire_four.addFirst("vest");
        attire_four.addFirst("robe");
        attire_four.addFirst("sock");
        attire_four.addFirst("belt");
        attire_four.addFirst("boot");
        attire_four.addFirst("cape");
        attire_four.addFirst("coat");
        attire_four.addFirst("gown");
        attire_four.addFirst("hood");
        attire_four.addFirst("kilt");
        attire_four.addFirst("suit");
        attire_four.addFirst("toga");
        attire_four.addFirst("sari");
        attire_four.addFirst("tutu");
        attire_five = new LinkedList<>();
        attire_five.addFirst("skirt");
        attire_five.addFirst("dress");
        attire_five.addFirst("apron");
        attire_five.addFirst("beret");
        attire_five.addFirst("parka");
        attire_five.addFirst("cloak");
        attire_five.addFirst("crown");
        attire_five.addFirst("frock");
        attire_five.addFirst("glove");
        attire_five.addFirst("jeans");
        attire_five.addFirst("scarf");
        attire_five.addFirst("shawl");
        attire_five.addFirst("smock");
        attire_five.addFirst("tunic");
        attire_six = new LinkedList<>();
        attire_six.addFirst("jacket");
        attire_six.addFirst("beanie");
        attire_six.addFirst("bikini");
        attire_six.addFirst("blazer");
        attire_six.addFirst("blouse");
        attire_six.addFirst("collar");
        attire_six.addFirst("diaper");
        attire_six.addFirst("fedora");
        attire_six.addFirst("fleece");
        attire_six.addFirst("helmet");
        attire_six.addFirst("jersey");
        attire_six.addFirst("kimono");
        attire_six.addFirst("poncho");
        attire_six.addFirst("sandal");
        attire_six.addFirst("shorts");
        attire_six.addFirst("tights");
        attire_six.addFirst("sweats");
        attire_six.addFirst("tuxedo");
        attire_six.addFirst("romper");
        attire_seven = new LinkedList<>();
        attire_seven.addFirst("sweater");
        attire_seven.addFirst("bandana");
        attire_seven.addFirst("legging");
        attire_seven.addFirst("leotard");
        attire_seven.addFirst("muffler");
        attire_seven.addFirst("necktie");
        attire_seven.addFirst("overall");
        attire_seven.addFirst("pajamas");
        attire_seven.addFirst("slipper");
        attire_seven.addFirst("sneaker");
        attire_seven.addFirst("uniform");
        attire_seven.addFirst("wetsuit");
        attire_eight = new LinkedList<>();
        attire_eight.addFirst("bathrobe");
        attire_eight.addFirst("breeches");
        attire_eight.addFirst("camisole");
        attire_eight.addFirst("cardigan");
        attire_eight.addFirst("flipflop");
        attire_eight.addFirst("headband");
        attire_eight.addFirst("knickers");
        attire_eight.addFirst("necklace");
        attire_eight.addFirst("bracelet");
        attire_eight.addFirst("raincoat");
        attire_eight.addFirst("shoelace");
        attire_eight.addFirst("snoeshoe");
        attire_eight.addFirst("sombrero");
        attire_eight.addFirst("swimsuit");
        attire_eight.addFirst("trousers");
        attire_nine = new LinkedList<>();
        attire_nine.addFirst("corduroys");
        attire_nine.addFirst("cowboyhat");
        attire_nine.addFirst("loincloth");
        attire_nine.addFirst("miniskirt");
        attire_nine.addFirst("nightgown");
        attire_nine.addFirst("pantihose");
        attire_nine.addFirst("petticoat");
        attire_nine.addFirst("underwear");
        attire_nine.addFirst("undercoat");
        attire_nine.addFirst("waistband");
        attire_ten = new LinkedList<>();
        attire_ten.addFirst("hikingboot");
        attire_ten.addFirst("nightdress");
        attire_ten.addFirst("suspenders");
        attire_ten.addFirst("sweatshirt");
        attire_ten.addFirst("trenchcoat");
        attire_ten.addFirst("undershirt");
        attire_eleven = new LinkedList<>();
        attire_eleven.addFirst("bathingsuit");
        attire_eleven.addFirst("cowboyboots");
        attire_eleven.addFirst("eveninggown");
        attire_eleven.addFirst("weddinggown");
        attire_eleven.addFirst("windbreaker");
        attire_twelve = new LinkedList<>();
        attire_twelve.addFirst("dressinggown");
        attire_twelve.addFirst("dinnerjacket");
        attire_twelve.addFirst("straitjacket");
        attire_twelve.addFirst("suitofarmour");
        attire_twelve.addFirst("undergarment");
    }


    public static void colors() {
        colors_three = new LinkedList<>();
        colors_three.addFirst("red");
        colors_three.addFirst("tan");
        colors_four = new LinkedList<>();
        colors_four.addFirst("blue");
        colors_four.addFirst("cyan");
        colors_four.addFirst("teal");
        colors_four.addFirst("gold");
        colors_four.addFirst("grey");
        colors_four.addFirst("lime");
        colors_four.addFirst("navy");
        colors_four.addFirst("pink");
        colors_four.addFirst("rose");
        colors_five = new LinkedList<>();
        colors_five.addFirst("amber");
        colors_five.addFirst("beige");
        colors_five.addFirst("black");
        colors_five.addFirst("brown");
        colors_five.addFirst("blond");
        colors_five.addFirst("coral");
        colors_five.addFirst("green");
        colors_five.addFirst("hazel");
        colors_five.addFirst("lilac");
        colors_five.addFirst("mauve");
        colors_five.addFirst("ochre");
        colors_five.addFirst("olive");
        colors_five.addFirst("sepia");
        colors_five.addFirst("white");
        colors_six = new LinkedList<>();
        colors_six.addFirst("auburn");
        colors_six.addFirst("bronze");
        colors_six.addFirst("copper");
        colors_six.addFirst("golden");
        colors_six.addFirst("indigo");
        colors_six.addFirst("maroon");
        colors_six.addFirst("orange");
        colors_six.addFirst("purple");
        colors_six.addFirst("russet");
        colors_six.addFirst("sienna");
        colors_six.addFirst("silver");
        colors_six.addFirst("violet");
        colors_six.addFirst("yellow");
        colors_seven = new LinkedList<>();
        colors_seven.addFirst("crimson");
        colors_seven.addFirst("emerald");
        colors_seven.addFirst("fuchsia");
        colors_seven.addFirst("magenta");
        colors_seven.addFirst("hotpink");
        colors_seven.addFirst("scarlet");
        colors_eight = new LinkedList<>();
        colors_eight.addFirst("amethyst");
        colors_eight.addFirst("bloodred");
        colors_eight.addFirst("babyblue");
        colors_eight.addFirst("burgundy");
        colors_eight.addFirst("charcoal");
        colors_eight.addFirst("lavender");
        colors_eight.addFirst("mahogany");
        colors_eight.addFirst("marigold");
        colors_eight.addFirst("offwhite");
        colors_eight.addFirst("sapphire");
        colors_nine = new LinkedList<>();
        colors_nine.addFirst("vermilion");
        colors_nine.addFirst("turquoise");
        colors_ten = new LinkedList<>();
        colors_ten.addFirst("aquamarine");
        colors_ten.addFirst("periwinkle");
        colors_ten.addFirst("terracotta");
        colors_eleven = new LinkedList<>();
        colors_eleven.addFirst("lapislazuli");
        colors_eleven.addFirst("burntsienna");
        colors_twelve = new LinkedList<>();
        colors_twelve.addFirst("emeraldgreen");

    }

    public static void body_parts() {
        body_parts_three = new LinkedList<>();
        body_parts_three.addFirst("eye");
        body_parts_three.addFirst("leg");
        body_parts_three.addFirst("arm");
        body_parts_three.addFirst("ear");
        body_parts_three.addFirst("hip");
        body_parts_three.addFirst("toe");
        body_parts_three.addFirst("jaw");
        body_parts_three.addFirst("rib");
        body_parts_three.addFirst("lip");
        body_parts_three.addFirst("abs");
        body_parts_three.addFirst("gut");
        body_parts_three.addFirst("bum");
        body_parts_three.addFirst("lap");
        body_parts_four = new LinkedList<>();
        body_parts_four.addFirst("hand");
        body_parts_four.addFirst("foot");
        body_parts_four.addFirst("knee");
        body_parts_four.addFirst("nail");
        body_parts_four.addFirst("hair");
        body_parts_four.addFirst("chin");
        body_parts_four.addFirst("nose");
        body_parts_four.addFirst("neck");
        body_parts_four.addFirst("shin");
        body_parts_four.addFirst("bone");
        body_parts_four.addFirst("butt");
        body_parts_four.addFirst("back");
        body_parts_four.addFirst("face");
        body_parts_four.addFirst("skin");
        body_parts_four.addFirst("iris");
        body_parts_four.addFirst("heel");
        body_parts_four.addFirst("palm");
        body_parts_five = new LinkedList<>();
        body_parts_five.addFirst("ankle");
        body_parts_five.addFirst("belly");
        body_parts_five.addFirst("brain");
        body_parts_five.addFirst("blood");
        body_parts_five.addFirst("chest");
        body_parts_five.addFirst("cheek");
        body_parts_five.addFirst("elbow");
        body_parts_five.addFirst("heart");
        body_parts_five.addFirst("liver");
        body_parts_five.addFirst("mouth");
        body_parts_five.addFirst("spine");
        body_parts_five.addFirst("skull");
        body_parts_five.addFirst("scalp");
        body_parts_five.addFirst("thigh");
        body_parts_five.addFirst("waist");
        body_parts_five.addFirst("thumb");
        body_parts_five.addFirst("wrist");
        body_parts_five.addFirst("torso");

        body_parts_six = new LinkedList<>();
        body_parts_six.addFirst("finger");
        body_parts_six.addFirst("tendon");
        body_parts_six.addFirst("throat");
        body_parts_six.addFirst("tongue");
        body_parts_six.addFirst("kidney");
        body_parts_six.addFirst("armpit");
        body_parts_six.addFirst("eyelid");
        body_parts_six.addFirst("muscle");
        body_parts_six.addFirst("rectum");
        body_parts_six.addFirst("spleen");
        body_parts_six.addFirst("saliva");

        body_parts_seven = new LinkedList<>();
        body_parts_seven.addFirst("earlobe");
        body_parts_seven.addFirst("cranium");
        body_parts_seven.addFirst("nostril");
        body_parts_seven.addFirst("abdomen");
        body_parts_seven.addFirst("eyebrow");
        body_parts_seven.addFirst("knuckle");
        body_parts_seven.addFirst("stomach");
        body_parts_seven.addFirst("midriff");
        body_parts_seven.addFirst("forearm");

        body_parts_eight = new LinkedList<>();
        body_parts_eight.addFirst("shoulder");
        body_parts_eight.addFirst("dandruff");
        body_parts_eight.addFirst("forehead");

        body_parts_nine = new LinkedList<>();
        body_parts_nine.addFirst("intestine");
        body_parts_nine.addFirst("moustache");
        body_parts_nine.addFirst("esophagus");

        body_parts_ten = new LinkedList<>();
        body_parts_ten.addFirst("fingernail");
        body_parts_ten.addFirst("quadriceps");

    }

}