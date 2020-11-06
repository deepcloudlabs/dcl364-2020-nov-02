package com.example.service.business;

import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.service.RandomNumberService;

@Service
@ConditionalOnProperty(name = "type", havingValue = "simple")
public class SimpleRandomNumberService implements RandomNumberService {
	private Random random = new Random();

	@Override
	public int generate(int min, int max) {
		System.err.println("SimpleRandomNumberService::generate");
		return random.nextInt(max - min + 1) + min;
	}

}
