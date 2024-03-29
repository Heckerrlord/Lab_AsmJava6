package com.poly.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Student;

@Controller
public class StudentController {

	@RequestMapping("bai32")
	public String student(Model model, @RequestParam("index") Optional<Integer> index) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String path = "E:\\HocTap\\workspace\\JAVA6\\SOF306-Lab2\\src\\main\\resources\\students.json";
		TypeReference<List<Student>> type = new TypeReference<List<Student>>() {
		};
		List<Student> list = mapper.readValue(new File(path), type);
		Student student = list.get(index.orElse(0));
		model.addAttribute("sv", student);
		model.addAttribute("index", index.orElse(0));
		return "bai32";
	}
}
