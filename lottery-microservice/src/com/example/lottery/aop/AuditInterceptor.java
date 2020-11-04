package com.example.lottery.aop;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Interceptor
@Audit
public class AuditInterceptor implements Serializable {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		var method = ic.getMethod();
		var args = ic.getParameters();
		System.err.println(String.format("%s runs at %s with parameters %s.", method.getName(), new Date(),
				Arrays.toString(args)));
		var result = ic.proceed();
		System.err.println(String.format("%s returns %s.", method.getName(), result));
		return result;
	}
}
