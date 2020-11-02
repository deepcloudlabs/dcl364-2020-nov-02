package com.example;

public class Square extends Shape {
	private double edge;

	public Square(double edge,String color) {
		super(color);
		this.edge = edge;
	}

	@Override
	public double area() {
		return edge * edge;
	}

	@Override
	public double circumference() {
		return 4. * edge;
	}

}
