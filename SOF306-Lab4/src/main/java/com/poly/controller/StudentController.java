package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Student;
import com.poly.bean.StudentMap;
import com.poly.dao.StudentDAO;

@Controller
public class StudentController {

	@Autowired
	StudentDAO dao;

	@RequestMapping("student")
	public String student(Model model) {
		Student student = new Student("", "", 0.0, true, "VN");
		model.addAttribute("form", student);
		StudentMap map = dao.findAll();
		model.addAttribute("items", map);
		return "student/index";
	}

	@RequestMapping("student/edit/{key}")
	public String edit(Model model, @PathVariable("key") String key) {
		model.addAttribute("key", key);
		Student student = dao.findByKey(key);
		model.addAttribute("form", student);
		StudentMap map = dao.findAll();
		model.addAttribute("items", map);
		return "student/index";
	}

	@RequestMapping("student/create")
	public String create(Student student) {
		dao.create(student);
		return "redirect:/student";
	}

	@RequestMapping("student/update/{key}")
	public String update(@PathVariable("key") String key, Student student) {
		dao.update(key, student);
		return "redirect:/student/edit/" + key;
	}

	@RequestMapping("student/delete/{key}")
	public String delete(@PathVariable("key") String key) {
		dao.delete(key);
		return "redirect:/student";
	}
}
