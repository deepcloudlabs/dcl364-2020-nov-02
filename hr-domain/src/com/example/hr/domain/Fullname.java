package com.example.hr.domain;

// Value Object -> Immutable Class
public final class Fullname {
	private final String first;
	private final String last;

	public Fullname(String first, String last) {
		this.first = first;
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	@Override
	public String toString() {
		return "Fullname [first=" + first + ", last=" + last + "]";
	}

}
