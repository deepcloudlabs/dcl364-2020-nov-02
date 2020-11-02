package com.example.service;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("unused")
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
		// Consumer<Integer> printInt = u -> System.out.println(u);
		Consumer<Integer> printInt = System.out::println;
		// How to define function since Java SE 8
		// 1. Lambda Expression
		// 2. Method Reference
		numbers.stream()
               .filter(Utility::ciftMi)
               .map(Utility::kare)
               .reduce(Utility::topla); // interface static method
	}

}

interface Utility {
	public static int topla(int x,int y) {
		return x + y;
	}
	public static int kare(int x) {
		return x * x;
	}
	public static boolean ciftMi(int x) {
		return x%2 == 0;
	}
}