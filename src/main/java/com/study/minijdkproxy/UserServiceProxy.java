package com.study.minijdkproxy;

import java.lang.reflect.Method;

public class UserServiceProxy implements UserService{
	UserService target;
	UserServiceProxy() {
		target = new UserServiceImpl();
	}

	@Override
	public void login() {
		System.out.println("before proxy");
		// try {
		// 	Method method = target.getClass().getMethod("login");
		// 	method.invoke(target);
		// }
		// catch (Exception e) {
		// 	System.out.println("抛出异常");
		// }
		target.login();
		System.out.println("after proxy");
	}
}
