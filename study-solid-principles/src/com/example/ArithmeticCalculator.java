package com.example;

public class ArithmeticCalculator implements AdvancedCalculator {

	@Override
	public double add(double x, double y) {
		return x + y;
	}

	@Override
	public double sub(double x, double y) {
		return x - y;

	}

	@Override
	public double mul(double x, double y) {
		return x * y;

	}

	@Override
	public double div(double x, double y) {
		return x / y;

	}

	@Override
	public double kilogramToPound(double kg) {
		return 0;
	}

	@Override
	public double kilometerToMile(double km) {
		return 0;
	}

}
