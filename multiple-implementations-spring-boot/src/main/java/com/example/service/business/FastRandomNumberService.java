package com.example.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.service.RandomNumberService;

@Service
//@ConditionalOnCloudPlatform(CloudPlatform.KUBERNETES)
@ConditionalOnProperty(name = "type", havingValue = "fast")
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
