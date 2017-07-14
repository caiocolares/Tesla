package br.com.tesla.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerController {	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView error(Exception ex) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("error", ex.getMessage());
		return model;
	}
	
}