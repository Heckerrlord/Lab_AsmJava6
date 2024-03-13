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
		model.addAttribute("message", "This is about page");
		return "home/about";
	}

	@RequestMapping("contact")
	public String contact(Model model) {
		model.addAttribute("message", "This is contact page");
		return "home/contact";
	}

//	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("admins")
	public String admins(Model model) {
		if (!request.isUserInRole("ADMIN")) {
			return "redirect:/auth/access/denied";
		}
		model.addAttribute("message", "Hello, Adminstrator");
		return "home/index";
	}

//	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@RequestMapping("users")
	public String users(Model model) {
		if (!(request.isUserInRole("ADMIN") || request.isUserInRole("USER"))) {
			return "redirect:/auth/access/denied";
		}
		model.addAttribute("message", "Hello, Staff");
		return "home/index";
	}

//	@PreAuthorize("isAuthenticated()")
	@RequestMapping("authenticated")
	public String authenticated(Model model) {
		if (request.getRemoteUser() == null) {
			return "redirect:/auth/login/form";
		}
		model.addAttribute("message", "Hello, Authenticated user");
		return "home/index";
	}

	@RequestMapping("thymeleaf1")
	public String thymeleaf1(Model model) {
		model.addAttribute("message", "Thymeleaf - Without Extras");
		return "home/thymeleaf1";
	}

	@RequestMapping("thymeleaf2")
	public String thymeleaf2(Model model) {
		model.addAttribute("message", "Thymeleaf - With Extras");
		return "home/thymeleaf2";
	}
}
