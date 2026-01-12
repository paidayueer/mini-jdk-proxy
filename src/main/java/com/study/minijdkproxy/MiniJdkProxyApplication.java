package com.study.minijdkproxy;

import com.study.minijdkproxy.dynamic.$proxy0;
import com.study.minijdkproxy.dynamic.LogMyInvocationHandler;
import com.study.minijdkproxy.dynamic.MyInvocationHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class MiniJdkProxyApplication {

	public static void main(String[] args) throws Exception {
		// MyProxy userProxy = new MyProxy(UserServiceImpl.class);
		// userProxy.invokeFn("login");
		// 静态代理：
		// UserService proxy = new UserServiceProxy();
		// proxy.login();
		// 动态代理
		// LogMyInvocationHandler 本质是拦截器，里面塞进你想要填充的日志信息;
		// $proxy0这个是jvm生成的代理类（此处我自己写），如果有多个实现类都是一样的MyInvocation，只会生成一个代理类，根据构造函数传入的实现类不同，会有不同的代理实例
		UserService target = new UserServiceImpl();
		MyInvocationHandler h = new LogMyInvocationHandler(target);
		UserService proxy0 = new $proxy0(h);
		proxy0.login();

	}

}
