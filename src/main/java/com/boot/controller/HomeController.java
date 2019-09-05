package com.boot.controller;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@RequestMapping("/staticPage")
	public ModelAndView index () {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("staticPage");
	    return modelAndView;
	}
	
	@RequestMapping("/index")
	public ModelAndView home () {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index");
	    return modelAndView;
	}
	
}


