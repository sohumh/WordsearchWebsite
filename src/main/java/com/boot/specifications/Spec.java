package com.boot.specifications;

import java.awt.List;
import java.util.LinkedList;

public class Spec {
	public int width;
    public int height;
    public String words;
    public String title;
    public String message;
    public int type;
    public LinkedList<String> categories;
    public int minLength;
    public LinkedList<String> wordList;
    public boolean[] answer;

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public String getWords() {
        return words;
    }
    
    public String getTitle() {
        return title;
    }
    
    public LinkedList<String> getCategories() {
        return categories;
    }
    
    public int getMinLength() {
        return minLength;
    }
    
    public String getMessage() {
        return message;
    }
    
    public int getType() {
        return type;
    }
    
    public LinkedList<String> getWordList() {
        return wordList;
    }
    
    public boolean[] getAnswer() {
        return answer;
    }
    
    public void setAnswer(boolean[] t) {
        this.answer = t;
    }
    
    public void setWordList(LinkedList<String> t) {
        this.wordList = t;
    }
    
    public void setType(int t) {
        this.type = t;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setMinLength(int length) {
        this.minLength = length;
    }

    public void setCategories(LinkedList<String> hobbieslist) {
        this.categories = hobbieslist;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setWords(String words) {
        this.words = words;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}

