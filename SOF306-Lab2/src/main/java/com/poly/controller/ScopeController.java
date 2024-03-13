package com.poly.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScopeController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@Autowired
	ServletContext app;

	@RequestMapping("bai31")
	public String scope(Model model) throws Exception {
		model.addAttribute("a", "I am in model");
		request.setAttribute("b", "I am in request");
		session.setAttribute("c", "I am in session");
		app.setAttribute("d", "I am in application");
		return "bai31";
	}
}
