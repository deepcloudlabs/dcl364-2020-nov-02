package com.example;

public abstract class Shape {
	private String color;

	public Shape(String color) {
		this.color = color;
	}

	abstract public double area();

	abstract public double circumference();

	public String getColor() {
		return color;
	}
}
