package com.poly.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private String name;
	private Boolean gender;
	private Double marks;
	private Contact contact;
	private List<String> subjects;
}