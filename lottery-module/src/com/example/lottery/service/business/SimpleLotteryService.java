package com.example.lottery.service.business;

import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.lottery.service.LotteryService;
import com.example.random.service.RandomNumberService;

public class SimpleLotteryService implements LotteryService {

	private RandomNumberService randomNumberService;

	@SuppressWarnings("unchecked")
	public SimpleLotteryService() {
		ServiceLoader<RandomNumberService> loader = ServiceLoader.load(RandomNumberService.class);
		var props = new Properties();
		try {
			props.load(new FileInputStream(new File("src","application.properties")));
			Class<? extends Annotation> clazz = (Class<? extends Annotation>) Class.forName("com.example.random.service."+props.getProperty("random.number.service"));
			for (RandomNumberService rns: loader) {
				if (rns.getClass().isAnnotationPresent(clazz))
					this.randomNumberService = rns;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> draw(int max, int size) {
		return IntStream.generate(() -> randomNumberService.generate(1, max)).distinct().limit(size).sorted().boxed()
				.collect(Collectors.toList());
	}

}
