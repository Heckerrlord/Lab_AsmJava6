package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.bean.Student;
import com.poly.bean.Student2;

@Controller
public class StudentController {

	@GetMapping("bai11")
	public String form(Model model) {
		Student student = new Student();
		student.setEmail("duyplusdz@gmail.com");
		student.setFullname("Nguyễn Hoàng Duy");
		student.setCountry("VN");
		student.setGender(true);
		model.addAttribute("sv", student);
		return "student/form";
	}

	@GetMapping("bai12")
	public String form2(Model model) {
		Student2 student = new Student2();
		model.addAttribute("sv", student);
		return "validation/form";
	}

	@PostMapping("bai11/save")
	public String save(@ModelAttribute("sv") Student form) {
		return "student/success";
	}

	@PostMapping("bai12/save")
	public String save2(Model model, @Validated @ModelAttribute("sv") Student2 form, Errors errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
			return "validation/form";
		}
		return "validation/validated";
	}
}
