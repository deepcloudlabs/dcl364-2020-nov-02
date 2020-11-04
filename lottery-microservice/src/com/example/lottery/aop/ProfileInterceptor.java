package com.example.lottery.aop;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Interceptor
@Profile
public class ProfileInterceptor implements Serializable {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		var start = System.currentTimeMillis();
		var result = ic.proceed();
		var stop = System.currentTimeMillis();
		System.err.println(String.format("%s runs %d ms.", ic.getMethod().getName(), (stop-start)));
		return result;
	}
}
