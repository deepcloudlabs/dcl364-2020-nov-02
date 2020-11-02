package com.example.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class StudyFunctionalProgramming {

	public static void main(String[] args) {
		// List<Integer> numbers = Arrays.asList(4,8,15,16,23,42)
		
		// Since Java SE 9: List.of() -> Immutable List
		List<Integer> numbers = List.of(4,8,15,16,23,42);
		
		int sum = 0;
		for (int number : numbers) {
			if (number % 2 == 0) // if even
				sum = sum + number * number ;
		}
		System.out.println("Sum: "+sum);
		
		// Functional Programming + Stream API
		
        Predicate<Integer> isEven = x -> x%2 == 0;
		Function<Integer,Integer> square = y -> y * y;
		BinaryOperator<Integer> accumulate = (s,z)->s+z;
		Consumer<Integer> printInt = u -> System.out.println(u);
		numbers.stream()
               .filter(isEven)
               .map(square)
               .reduce(Integer::sum);
	}

}
