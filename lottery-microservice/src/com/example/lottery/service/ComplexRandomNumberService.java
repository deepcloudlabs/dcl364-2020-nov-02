package com.example.lottery.service;

import java.security.SecureRandom;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
@Alternative
public class ComplexRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
    	System.err.println("ComplexRandomNumberService::generate()");
		return new SecureRandom().nextInt(max-min+1)+min;
	}

}
