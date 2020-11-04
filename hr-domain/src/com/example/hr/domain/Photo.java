package com.example.hr.domain;

import java.util.Objects;

// ValueObject
public final class Photo {
	private final byte[] values;

	private Photo(byte[] values) {
		this.values = values;
	}

	public byte[] getValues() {
		return values;
	}

	public static Photo valueOf(byte[] values) {
		Objects.requireNonNull(values);
		return new Photo(values);
	}
}
