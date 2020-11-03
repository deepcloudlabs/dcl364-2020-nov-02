package com.example.app;

import java.lang.reflect.Proxy;

import com.example.aop.CachingHandler;
import com.example.aop.LoggingHandler;
import com.example.service.Calculator;
import com.example.service.business.SimpleCalculator;

public class CalculatorApp {

	public static void main(String[] args) {
		var calculator = new SimpleCalculator();
		var clazz = calculator.getClass();
		var clzLoader = clazz.getClassLoader();
		var interfaces = clazz.getInterfaces();
		var proxy1 = Proxy.newProxyInstance(clzLoader, interfaces, 
				new CachingHandler(calculator)); 
		var proxy2 = (Calculator) Proxy.newProxyInstance(
				clzLoader, interfaces, new LoggingHandler(proxy1));
		System.err.println(proxy1.getClass().getName());
		System.err.println(proxy2.getClass().getName());
		for(var i=0;i<10;++i) {
			var result = proxy2.add(3, 5);
			System.err.println("result is "+result);			
		}
	}

}
