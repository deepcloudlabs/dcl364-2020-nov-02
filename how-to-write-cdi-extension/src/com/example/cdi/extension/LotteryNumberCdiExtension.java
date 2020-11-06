package com.example.cdi.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import com.example.service.RandomNumber;

// Marker Interface
public class LotteryNumberCdiExtension implements Extension {

	public <T> void initializeRandomNumberExtension(@Observes ProcessInjectionTarget<T> pit) {
		System.err.println("pit: "+pit);
		var annotatedType = pit.getAnnotatedType();
		var fields = annotatedType.getJavaClass().getDeclaredFields();
		for (var field : fields) {
			if (field.isAnnotationPresent(RandomNumber.class)) {
				pit.setInjectionTarget(new RandomNumberInjectionTarget<>(pit.getInjectionTarget()));
			}
		}
	}
}
