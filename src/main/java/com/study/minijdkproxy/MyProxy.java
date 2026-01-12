package com.study.minijdkproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyProxy {
	private Object target;
	public MyProxy(Class clazz) throws Exception {
		Constructor<?> c = clazz.getConstructor();
		target = c.newInstance();
	}
	public void invokeFn(String methodName, Object... args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		System.out.println("before proxy");
		Method method = target.getClass().getMethod(methodName);
		method.invoke(target,args);
		System.out.println("after proxy");
	}
}
