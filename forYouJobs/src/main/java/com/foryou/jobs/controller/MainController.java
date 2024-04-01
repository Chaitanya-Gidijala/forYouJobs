package com.foryou.jobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	@GetMapping("/showMyLoginPage")
	public String loginPage() {
		return "sign-in";
	}

	
	
	
}
