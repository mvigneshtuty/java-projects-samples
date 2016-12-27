package org.nfjs.spring.aop.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.ConnectException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.nfjs.spring.aop.core.Service;
import org.nfjs.spring.aop.service.MessageService;

@RunWith(MockitoJUnitRunner.class)
public class MessageAspectTest {

	
	Service messageService = mock(MessageService.class);

	@Test
	public void testServiceAspect() {
		
		try {
			System.out.println("Invoking mock test.");
			verify(messageService).send("Hello SpringAop", "AC");
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		
	}
}
