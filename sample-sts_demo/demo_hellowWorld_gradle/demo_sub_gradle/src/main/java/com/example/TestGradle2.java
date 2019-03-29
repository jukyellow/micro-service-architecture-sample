package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class TestGradle2 {
	//@Autowired 
	//MsgCore core;
	
	public static void main(String[] args) {
		SpringApplication.run(TestGradle2.class, args);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getHelloWorld(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
	
/*	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getHelloWorld() {
		return "index";
	}*/
	
	@RequestMapping(value="/call", method=RequestMethod.GET)
	//@ResponseBody
	public MsgCore callCore() {
		return new MsgCore("test1","call core msg");
	}
}
