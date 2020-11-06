package com.example.lottery.app;

import java.lang.reflect.InvocationTargetException;
import java.util.ServiceLoader;

import com.example.lottery.service.LotteryService;

public class LotteryApp {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		ServiceLoader<LotteryService> loader = ServiceLoader.load(LotteryService.class);
		LotteryService lotteryService = loader.findFirst().get();
		System.err.println(lotteryService.getClass().getName());
		lotteryService.draw(50, 6, 10).forEach(System.out::println);
	}
}
