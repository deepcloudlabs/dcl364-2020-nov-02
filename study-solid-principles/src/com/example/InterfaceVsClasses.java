package com.example;

public class InterfaceVsClasses {

	public static void main(String[] args) {
		A a = new B();
		System.out.println(a instanceof H);

	}

}

class A {}
class B extends A {}
class C {}
class D {}
interface H {}