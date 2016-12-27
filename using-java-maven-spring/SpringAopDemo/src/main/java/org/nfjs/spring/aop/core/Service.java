package org.nfjs.spring.aop.core;

import java.net.ConnectException;

public interface Service {
	void send(String message, String receiver) throws ConnectException;
}
