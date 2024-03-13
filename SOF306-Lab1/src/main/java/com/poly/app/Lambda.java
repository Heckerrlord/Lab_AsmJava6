package com.poly.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.poly.bean.Student;

public class Lambda {

	static List<Student> list = Arrays.asList(new Student("Nguyễn Hoàng Duy", true, 7.5),
			new Student("Trần Thị Thu Hương", false, 5.5), new Student("Phạm Đức Cường", true, 9.5),
			new Student("Lê Thị Mỹ Hồng", false, 6.5), new Student("Đoàn Thị Kim Ty", false, 8.0));

	public static void main(String[] args) {
//		demo1();
//		demo2();
//		demo3();
		demo4();

	}

	private static void demo4() {
//		DemoInter o = new DemoInter() {
//			@Override
//			public void m1(int x) {
//				System.out.println(x);
//			}
//		};
//		o.m1(2022);

//		DemoInter o = (int x) -> {
//			System.out.println(x);
//		};
//		o.m1(2022);

		DemoInter o = x -> System.out.println(x);
		o.m1(2022);
	}

	private static void demo3() {
		Collections.sort(list, (sv1, sv2) -> sv1.getMarks().compareTo(sv2.getMarks()));
		list.forEach(sv -> {
			System.out.println(">> Name: " + sv.getName());
			System.out.println(">> Marks: " + sv.getMarks());
			System.out.println();
		});
	}

	private static void demo2() {
		List<Student> list = Arrays.asList(new Student("Nguyễn Hoàng Duy", true, 7.5),
				new Student("Trần Thị Thu Hương", false, 5.5), new Student("Phạm Đức Cường", true, 9.5),
				new Student("Lê Thị Mỹ Hồng", false, 6.5), new Student("Đoàn Thị Kim Ty", false, 8.0));
		list.forEach(sv -> {
			System.out.println(">> Name: " + sv.getName());
			System.out.println(">> Marks: " + sv.getMarks());
			System.out.println();
		});
	}

	private static void demo1() {
		List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
		list.forEach(n -> System.out.println(n));
	}

	@FunctionalInterface
	interface DemoInter {
		void m1(int x);

		default void m2() {
		};

		public static void m3() {
		};
	}
}
