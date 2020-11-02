package com.example;

import java.util.List;

public class StudyLsp {

	public static void main(String[] args) {
		var shapes = List.of(new Rectangle(5, 10, "Red"),
				new Square(7,"Blue"));
		System.out.println(testLsp(shapes));
	}

	public static double testLsp(List<Shape> shapes) {
		return shapes.stream().mapToDouble(Shape::area).sum();
	}
}
