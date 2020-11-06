package com.example.service.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ejb.Singleton;
import javax.inject.Inject;

import com.example.service.LotteryService;
import com.example.service.RandomNumberService;

@Singleton
public class StandardLotteryService implements LotteryService {
	@Inject
    private RandomNumberService randomNumberService;
	
	@Override
	public List<Integer> draw(int max, int size) {
		return IntStream.generate(() -> randomNumberService.generate(1, max)).distinct().limit(size).sorted().boxed()
				.collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column).mapToObj(i -> this.draw(max, size)).collect(Collectors.toList());
	}

}
