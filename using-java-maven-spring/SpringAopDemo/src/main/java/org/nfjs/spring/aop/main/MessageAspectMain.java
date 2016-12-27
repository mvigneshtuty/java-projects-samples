package org.nfjs.spring.aop.main;

import java.net.ConnectException;

import org.nfjs.spring.aop.core.Service;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageAspectMain {

	public static void main(String[] args) throws ConnectException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aopdemo-config.xml");
		Service messageService = (Service) context.getBean("messageService");
		messageService.send("Hello SpringAop - spam", "AC");
		context.close();
	}

}
