package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String getHelloWorld() {
		return "Hellow Spring Boot (2018.12.22,juk)";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ModelAndView getHelloWorld(ModelAndView mv) {
		System.out.println("call test");
		mv.setViewName("testindex");
		return mv;
	}

}
