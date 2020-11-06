package com.example.service.business;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.LotteryService;
import com.example.service.RandomNumberService;

@Service
public class StandardLotteryService implements LotteryService {
	@Autowired
	private List<RandomNumberService> randomNumberServices;
	private AtomicInteger counter= new AtomicInteger(0);
		
	@Override
	public List<Integer> draw(int max, int size) {
		var index = counter.getAndIncrement() % randomNumberServices.size();
		var randomNumberService = randomNumberServices.get(index);
		return IntStream.generate(() -> randomNumberService.generate(1, max)).distinct().limit(size).sorted().boxed()
				.collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column).mapToObj(i -> this.draw(max, size)).collect(Collectors.toList());
	}

}
