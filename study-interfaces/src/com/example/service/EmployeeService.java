package com.example.service;
/**
 *      ✘ new EmployeeService()
 * 
 *
 */
@FunctionalInterface // Annotation for Compiler
public abstract interface EmployeeService {
	 int x= 42; // public static final : implicit
	 // not allowed: EmployeeService();
	 void fun(); // abstract public
	 // Since Java SE 8
	 // 1. default method -> default implementation
	 //    API v1 -> API v2 : backward compatibility
	 default void gun() {
		 System.out.println("Default gun() implementation.");
	 }
	 // 2. public static method
	 // Functional Programming -> utility method
	 static void sun() {
		 System.out.println("static sun() method.");		 
	 }
	 // 3. Functional Interface: Single Abstract Method (SAM)
	 // Marker Interface (0): Serializable
	 // Functional Interface (1 -> SAM): Runnable, Callable, ...
	 
	 // Since Java SE 9
	 // 1. private concrete method 
	 // 2. private static method
}
