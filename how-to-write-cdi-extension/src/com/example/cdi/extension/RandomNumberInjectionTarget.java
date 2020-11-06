package com.example.cdi.extension;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

import com.example.service.RandomNumber;

public class RandomNumberInjectionTarget<T> implements InjectionTarget<T> {
	private final InjectionTarget<T> injectionTarget;

	public RandomNumberInjectionTarget(InjectionTarget<T> injectionTarget) {
		this.injectionTarget = injectionTarget;
	}

	@Override
	public void dispose(T instance) {
		injectionTarget.dispose(instance);
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		return injectionTarget.getInjectionPoints();
	}

	@Override
	public T produce(CreationalContext<T> ctx) {
		return injectionTarget.produce(ctx);
	}

	@Override
	public void inject(T instance, CreationalContext<T> ctx) {
		setRandomNumbers(instance, ctx);

	}

	private void setRandomNumbers(T instance, CreationalContext<T> ctx) {
		var clazz = instance.getClass();
		for (var field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(RandomNumber.class)) {
				var randomNumber = field.getAnnotation(RandomNumber.class);
				var min = randomNumber.min();
				var max = randomNumber.max();
				var size = randomNumber.size();
				var sorted = randomNumber.sorted();
				var distinct = randomNumber.distinct();
				field.setAccessible(true);
				try {
					var stream = ThreadLocalRandom.current().ints(min, max);
					if (distinct)
						stream = stream.distinct();
					stream = stream.limit(size);
					if (sorted)
						stream = stream.sorted();
					var numbers = stream.boxed().collect(Collectors.toList());
					field.set(instance, numbers);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.err.println("Error in injection: " + e.getMessage());
				}
				field.setAccessible(false);
			}
		}

	}

	@Override
	public void postConstruct(T instance) {
		injectionTarget.postConstruct(instance);
	}

	@Override
	public void preDestroy(T instance) {
		injectionTarget.preDestroy(instance);
	}

}
