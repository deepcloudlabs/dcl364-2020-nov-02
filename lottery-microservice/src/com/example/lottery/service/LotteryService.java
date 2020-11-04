package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.aop.Audit;
import com.example.lottery.aop.Profile;

// Domain Model
// CDI Bean -> CDI Component 
@Named
@Singleton
public class LotteryService {
	// private RandomNumberService randomNumberService;
	@Inject
	@Any
	private Instance<RandomNumberService> instances;
	private AtomicInteger counter = new AtomicInteger();
	private List<RandomNumberService> services;

	@PostConstruct
	public void init() {
		services = instances.stream().collect(Collectors.toList());
        services.stream()
                .map(Object::getClass)
                .map(Class::getName)
                .forEach(System.out::println);
	}
	// Dependency Injection using Constructor
//   @Inject
//   public LotteryService(@Complex RandomNumberService randomNumberService) {
//		this.randomNumberService = randomNumberService;
//   }

	// Dependency Injection using setter
//    public void setRandomNumberService(RandomNumberService randomNumberService) {
//	   this.randomNumberService = randomNumberService;
//    }

	public List<Integer> draw(int max, int size) {
		var index = counter.getAndIncrement() % services.size();
		var randomNumberService = services.get(index);
		return IntStream.generate(() -> randomNumberService.generate(1, max)) // stream api
				.distinct().limit(size).sorted() // int
				// .mapToObj(Integer::valueOf) // Integer
				.boxed() // mapping method
				.collect(Collectors.toList());
	}

	@Audit
	@Profile
	public List<List<Integer>> draw(int max, int size, int column) {
		// Round-robin between all implementations
		var index = counter.getAndIncrement() % services.size();
		var randomNumberService = services.get(index);
		System.err.println(randomNumberService.getClass().getName());
		return IntStream.range(0, column).mapToObj(i -> draw(max, size)).collect(Collectors.toList());
	}
}
