package com.study.minijdkproxy.dynamic;

import java.lang.reflect.Method;

public interface MyInvocationHandler {
	Object invoke(Method m, Object... args) throws Exception;
}
