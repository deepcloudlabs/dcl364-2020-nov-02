package com.example.lottery.decorator;

import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import com.example.lottery.service.LotteryService;

@Decorator
public class LotteryServiceDecorator implements LotteryService {
    @Inject
	@Any
	@Delegate
	private LotteryService lotteryService;
	
	@Override
	public List<Integer> draw(int max, int size) {
		var numbers = lotteryService.draw(max, size);
		var sum = numbers.stream().reduce(Integer::sum).get();
		numbers.add(sum);
		return numbers;
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		var lotteryNumbers = lotteryService.draw(max, size, column);
		for (var numbers: lotteryNumbers) {
			var sum = numbers.stream().reduce(Integer::sum).get();
			numbers.add(sum);
		}
		return lotteryNumbers;
	}

}
