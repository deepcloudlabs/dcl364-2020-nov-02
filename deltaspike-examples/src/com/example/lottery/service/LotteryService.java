package com.example.lottery.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.ConfigProperty;

@Stateless
public class LotteryService {
	@Inject
    @ConfigProperty(name = "lottery.max")
	private int lotteryMax;

	@Inject
	@ConfigProperty(name = "lottery.size")
	private int lotterySize;
	
	@Schedule(second = "*/5", hour = "*", minute = "*")
	public void draw() {
		var numbers = ThreadLocalRandom.current()
				.ints(1, lotteryMax)
				.distinct()
				.limit(lotterySize)
				.sorted()
				.boxed()
				.collect(Collectors.toList());
		System.err.println(numbers);
	}
	
}
