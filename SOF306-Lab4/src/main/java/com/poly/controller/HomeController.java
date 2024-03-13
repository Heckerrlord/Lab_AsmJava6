package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("about")
	public String about() {
		return "home/about";
	}

	@RequestMapping("contact")
	public String contact() {
		return "home/contact";
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}
}
