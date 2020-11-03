package com.example;

import java.util.Set;

public class StudyIterator {

	public static void main(String[] args) {
		var numbers = Set.of(4, 8, 15, 16, 23, 42);
		
		// outer loop #1
		for (var iter = numbers.iterator(); iter.hasNext();) {
			var number = iter.next();
			System.out.println("number: " + number);
		}
		// outer loop #2 (since java se 5)
		for (var number : numbers) {
			System.out.println("number: " + number);
		}
		// inner loop #1 -> Spliterator
		numbers.forEach(System.out::println);
		// inner loop #2 -> Filter/Map/Reduce
		numbers.stream().forEach(System.out::println);
		// inner loop #3 -> Parallel Programming/Multi-core Programming
		numbers.parallelStream().forEach(System.out::println);
		// Lazy Evaluation
		var sum = numbers.stream().parallel().filter(n -> n % 2 == 0).map(n -> n * n).sorted().reduce(0,
				(s, n) -> s + n);

		System.out.println("sum: " + sum);
	}
}
