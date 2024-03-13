package com.poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	HttpServletRequest request;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("message", "This is home page");
		return "home/index";
	}

	@RequestMapping("about")
	public String about(Model model) {
		model.addAttribute("message", "This is introduction page");
		return "home/about";
	}

	@RequestMapping("contact")
	public String contact(Model model) {
		model.addAttribute("message", "This is contact page");
		return "home/contact";
	}

	@RequestMapping("admins")
	public String admins(Model model) {
		if (!request.isUserInRole("ADMIN")) {
			return "redirect:/auth/access/denied";
		}
		model.addAttribute("message", "Hello, Adminstrator");
		return "home/index";
	}

	@RequestMapping("users")
	public String users(Model model) {
		if (!(request.isUserInRole("ADMIN") || request.isUserInRole("USER"))) {
			return "redirect:/auth/access/denied";
		}
		model.addAttribute("message", "Hello, Staff");
		return "home/index";
	}

	@RequestMapping("authenticated")
	public String authenticated(Model model) {
		if (request.getRemoteUser() == null) {
			return "redirect:/auth/login/form";
		}
		model.addAttribute("message", "Hello, Authenticated user");
		return "home/index";
	}

	@RequestMapping("authorize")
	public String authorize() {
		return "home/authorize";
	}
}
