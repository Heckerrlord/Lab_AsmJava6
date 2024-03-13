package com.poly.app;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJSON {

	public static void main(String[] args) throws Exception {
//		demo1();
		demo2();
	}

	private static void demo2() throws Exception {
		String path = "E:\\HocTap\\workspace\\JAVA6\\SOF306-Lab1\\src\\main\\resources\\students.json";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode students = mapper.readTree(new File(path));
		students.iterator().forEachRemaining(student -> {
			System.out.println(">> Name: " + student.get("name").asText());
		});

	}

	private static void demo1() throws Exception {
//		String json = "{\n\t\"name\" : \"Nguyễn Hoàng Duy\",\n\t\"gender\" : true,\n\t\"marks\" : 8.5,\n\t\""
//				+ "contact\" : {\n\t\t\"email\" : \"duyplusdz@gmail.com\",\n\t\t\"phone\" : \"0123456789\"\n\t},\n\t\""
//				+ "subjects\" : [ \"SOF302\", \"SOF306\" ]\n}";
		String path = "E:\\HocTap\\workspace\\JAVA6\\SOF306-Lab1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
//		JsonNode student = mapper.readTree(json);
		JsonNode student = mapper.readTree(new File(path));

		System.out.println(">> Name: " + student.get("name").asText());
		System.out.println(">> Marks: " + student.get("marks").asDouble());
		System.out.println(">> Gender: " + student.get("gender").asBoolean());
		System.out.println(">> Email: " + student.get("contact").get("email").asText());
		System.out.println(">> Phone: " + student.findValue("phone").asText());
		student.get("subjects").iterator().forEachRemaining(subject -> {
			System.out.println(">> Subject: " + subject.asText());
		});
	}

}
