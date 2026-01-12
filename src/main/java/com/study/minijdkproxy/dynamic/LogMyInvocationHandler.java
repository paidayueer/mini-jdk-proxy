package com.study.minijdkproxy.dynamic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/*
* 拦截器，里面塞进你想要填充的日志信息
* */
public class LogMyInvocationHandler implements MyInvocationHandler{
	private Object target;
	public LogMyInvocationHandler(Object t){
		target = t;
	}

	@Override
	public Object invoke(Method m, Object... args) throws Exception {
		System.out.println("before proxy");
		Object result = m.invoke(target,args);
		System.out.println("after proxy");
		return result;
	}
}
