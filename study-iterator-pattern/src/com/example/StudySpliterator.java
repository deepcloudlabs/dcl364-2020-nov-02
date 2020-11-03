package com.example;

import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudySpliterator {

	public static void main(String[] args) {
		var numbers1 = IntStream.range(0, 10).boxed().collect(Collectors.toList());
		var spliterator = numbers1.spliterator();
		// iterator
		while (spliterator.tryAdvance(System.out::println));		
        // Job/Task Stealing Algorithm -> ForkJoin Framework (Java SE 7)
		var numbers2 = IntStream.range(0, 10).boxed().collect(Collectors.toList());
		var spliterator1 = numbers2.spliterator();
		var spliterator2 = spliterator1.trySplit();
		var spliterator3 = spliterator2.trySplit();
		System.out.println("spliterator1:");	
		while (spliterator1.tryAdvance(System.out::println));		
		System.out.println("spliterator2:");	
		while (spliterator2.tryAdvance(System.out::println));		
		System.out.println("spliterator3:");	
		while (spliterator3.tryAdvance(System.out::println));		
	
		System.err.println("Characteristics: " + spliterator3.characteristics());
		// if it's capable of returning an exact number of elements with the
		// estimateSize() method
		System.err.println("SIZED     :" + spliterator3.hasCharacteristics(Spliterator.SIZED));
		// if it's iterating through a sorted source
		System.err.println("SORTED    :" + spliterator3.hasCharacteristics(Spliterator.SORTED));
		// if we split the instance using a trySplit() method and obtain Spliterators
		// that are SIZED as well
		System.err.println("SUBSIZED  :" + spliterator3.hasCharacteristics(Spliterator.SUBSIZED));
		// if source can be safely modified concurrently
		System.err.println("CONCURRENT:" + spliterator3.hasCharacteristics(Spliterator.CONCURRENT));
		// if for each pair of encountered elements x, y, !x.equals(y)
		System.err.println("DISTINCT  :" + spliterator3.hasCharacteristics(Spliterator.DISTINCT));
		// if elements held by source can't be structurally modified
		System.err.println("IMMUTABLE :" + spliterator3.hasCharacteristics(Spliterator.IMMUTABLE));
		// if source holds nulls or not
		System.err.println("NONNULL   :" + spliterator3.hasCharacteristics(Spliterator.NONNULL));
		// if iterating over an ordered sequence
		System.err.println("ORDERED   :" + spliterator3.hasCharacteristics(Spliterator.ORDERED));		
				
	}

}
