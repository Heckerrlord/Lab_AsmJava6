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

	@RequestMapping({ "/", "/home", "/home/index" })
	public String index() {
		return "redirect:/home/index.html#!/product";
	}

	@RequestMapping("admins")
	public String admins(Model model) {
		if (!request.isUserInRole("DIRE")) {
			return "redirect:/login";
		}
		return "home/index";
	}

	@RequestMapping("users")
	public String users(Model model) {
		if (!(request.isUserInRole("DIRE") || request.isUserInRole("STAF"))) {
			return "redirect:/login";
		}
		return "home/index";
	}

	@RequestMapping("authenticated")
	public String authenticated(Model model) {
		if (request.getRemoteUser() == null) {
			return "redirect:/login";
		}
		return "home/index";
	}
}
