package com.poly.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Student;

@Controller
public class HomeController {

	@RequestMapping("bai2")
	public String home(Model model) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String path = "E:\\HocTap\\workspace\\JAVA6\\SOF306-Lab2\\src\\main\\resources\\student.json";
		Student student = mapper.readValue(new File(path), Student.class);
		model.addAttribute("sv", student);
		model.addAttribute("message", "Welcome to Thymeleaf");
		return "bai2";
	}
}
