package com.example.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

public class CachingHandler implements InvocationHandler {

	private Object target;
	private Map<Integer, Map<Integer,Object>> cache = new WeakHashMap<>();
	
	public CachingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var hashCode = method.hashCode();
		var methodCache = cache.get(hashCode);
		int parameterHashCode = Arrays.hashCode(args);
		if (Objects.isNull(methodCache)) {
			var result = method.invoke(target, args);
			methodCache = new WeakHashMap<Integer,Object>();
			cache.put(hashCode, methodCache);
			methodCache.put(parameterHashCode, result);
			return result;
		}
		var cacheResult = methodCache.get(parameterHashCode);
		if (Objects.isNull(cacheResult)) {
			cacheResult = method.invoke(target, args);
			methodCache.put(parameterHashCode, cacheResult);
		}
		return cacheResult;
	}

}
