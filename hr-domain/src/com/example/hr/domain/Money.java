package com.example.hr.domain;

// Value Object -> Immutable
public class Money {
	private final double value;
	private final FiatCurrency currency;

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Money of(double value) {
		return Money.of(value, FiatCurrency.TRY);
	}
	
	public static Money of(double value, FiatCurrency currency) {
		if (value <= 0.)
			throw new IllegalArgumentException("Money value cannot be negative.");
		return new Money(value, currency);
	}

	@Override
	public String toString() {
		return "Money [value=" + value + ", currency=" + currency + "]";
	}
	
}
