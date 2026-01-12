package com.study.minijdkproxy.dynamic;

import com.study.minijdkproxy.UserService;

import java.lang.reflect.Method;

/*
* 这个是jvm生成的代理类（此处我自己写），如果有多个实现类都是一样的MyInvocation，只会生成一个代理类，根据构造函数传入的实现类不同，会有不同的代理实例*/
public class $proxy0 implements UserService {
	private MyInvocationHandler h;
	public $proxy0(MyInvocationHandler handler) {
		h = handler;
	}
	@Override
	public void login() {
		try {
			Method m = UserService.class.getMethod("login");
			h.invoke(m);
		}
		catch (Exception e) {
			System.out.println("login方法获取失败");
		}
	}
}
