package com.example.lottery.service;

import java.util.concurrent.ThreadLocalRandom;

public class FastRandomNumberService implements RandomNumberService {
	
    @Override
	public int generate(int min, int max) {
    	return ThreadLocalRandom.current().nextInt(min, max);
    }
}
