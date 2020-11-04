package com.example.lottery.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.aop.Audit;

@Named
@Singleton
//@Default
@Fast
@Audit
public class FastRandomNumberService implements RandomNumberService {
	
    @Override
	public int generate(int min, int max) {
    	System.err.println("FastRandomNumberService::generate()");
    	return ThreadLocalRandom.current().nextInt(min, max);
    }
}
