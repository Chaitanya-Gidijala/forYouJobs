package com.foryou.jobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class InterviewGuidence {

	@GetMapping("/technical-interview-questions")
	public String technicalQuestions() {
		return "technical-interview";
	}
	
	@GetMapping("/technical-interview/java")
	public String javaInterview() {
		return "interview-java";
	}
	@GetMapping("/technical-interview/spring-boot")
	public String springBootInterview() {
		return "interview-springboot";
	}
	
	
}
