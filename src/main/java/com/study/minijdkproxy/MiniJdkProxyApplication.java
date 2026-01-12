package com.study.minijdkproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class MiniJdkProxyApplication {

	public static void main(String[] args) throws Exception {
		// MyProxy userProxy = new MyProxy(UserServiceImpl.class);
		// userProxy.invokeFn("login");
		UserService proxy = new UserServiceProxy();
		proxy.login();
	}

}
