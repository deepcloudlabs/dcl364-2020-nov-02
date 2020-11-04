package com.example.lottery.service;

import java.security.SecureRandom;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.aop.Profile;

@Named
@Singleton
//@Alternative
@Complex
@Profile
public class ComplexRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
    	System.err.println("ComplexRandomNumberService::generate()");
		return new SecureRandom().nextInt(max-min+1)+min;
	}

}
