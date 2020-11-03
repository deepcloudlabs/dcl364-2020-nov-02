package com.example.lottery.service;

import java.security.SecureRandom;

public class ComplexRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		return new SecureRandom().nextInt(max-min+1)+min;
	}

}
