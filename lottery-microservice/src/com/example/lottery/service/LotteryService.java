package com.example.lottery.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Named;
import javax.inject.Singleton;

// CDI Bean -> CDI Component 
@Named
@Singleton
public class LotteryService {
   private RandomNumberService randomNumberService;
	
   // Dependency Injection using Constructor
   public LotteryService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}
   
   // Dependency Injection using setter
    public void setRandomNumberService(RandomNumberService randomNumberService) {
	   this.randomNumberService = randomNumberService;
    }

	public List<Integer> draw(int max, int size){
	   return IntStream.generate( () -> randomNumberService.generate(1, max)) // stream api
			                   .distinct()
			                   .limit(size)
			                   .sorted() // int
			                   //.mapToObj(Integer::valueOf) // Integer
			                   .boxed() // mapping method
			                   .collect(Collectors.toList());
   }
   public List<List<Integer>> draw(int max, int size,int column){
	   System.err.println(randomNumberService.getClass().getName());
	   return IntStream.range(0, column)
			           .mapToObj(i -> draw(max,size))
			           .collect(Collectors.toList());
   }
}
