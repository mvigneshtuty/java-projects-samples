package org.nfjs.spring.aop.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MessageServiceMonitor {

	@Pointcut("execution(** org.nfjs.spring.aop.core.Service.send(..))")
	public void sendMessage() {

	}

	@Before("sendMessage()")
	public void triggerPreSendAlert() {
		System.out.println("Triggering PRE-send alert...");
	}

	@AfterReturning("sendMessage()")
	public void triggerPostSendAlert() {
		System.out.println("Triggering POST-send alert...");
	}
	
	@AfterThrowing("sendMessage()")
	public void messageSendErrorHandler(){
		System.out.println("Moving the message to separate queue for resending...");
	}
}
