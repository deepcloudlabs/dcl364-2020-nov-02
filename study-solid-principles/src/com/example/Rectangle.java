package com.example;

public class Rectangle extends Shape {
    private double width, height;
    
	public Rectangle(double width, double height,String color) {
		super(color);
		this.width = width;
		this.height = height;
	}

	@Override
	public double area() {
		return width * height;
	}

	@Override
	public double circumference() {
		return 2.0 * (width + height);
	}

}
