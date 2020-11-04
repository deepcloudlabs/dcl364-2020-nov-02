package com.example.hr.domain;

// Value Object
public final class BirthYear {
	private final int value;

	private BirthYear(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static BirthYear valueOf(int value) {
		if (value >= 2004)
			throw new IllegalArgumentException("Wrong value");
		return new BirthYear(value);
	}

	@Override
	public String toString() {
		return "BirthYear [value=" + value + "]";
	}

}
