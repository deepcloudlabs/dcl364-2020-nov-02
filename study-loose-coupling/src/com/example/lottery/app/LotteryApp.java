package com.example.lottery.app;

import com.example.lottery.service.ComplexRandomNumberService;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

public class LotteryApp {
	public static void main(String[] args) {
		// Single Responsibility ✔
		// Loose Coupling -> Interface ✔
		// Open-Closed Principle -> Interface ✔
		// Concrete -> Interface (unchanged) + Implementation (possible to change in the future)
        // Client depends on Interface
		// Dependency Injection ✘ --> Reference Initialization
		// Dependency Injection Frameworks: Spring + Java EE 6+ (CDI)
		// Constructor/Setter/Field Injection
		RandomNumberService randomNumberService = 
        		new ComplexRandomNumberService();
		LotteryService lotteryService = 
				new LotteryService(randomNumberService );
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println); // Method Reference
	}
}
