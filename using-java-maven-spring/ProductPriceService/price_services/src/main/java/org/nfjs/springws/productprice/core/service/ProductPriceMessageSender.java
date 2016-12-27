package org.nfjs.springws.productprice.core.service;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.nfjs.springws.pcatalog.domain.request.GetProductRequestType;
import org.nfjs.springws.pcatalog.domain.request.ObjectFactory;
import org.nfjs.springws.productprice.domain.request.GetProductPriceRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

@Component(value = "messageSender")
public class ProductPriceMessageSender {

	@Autowired
	@Qualifier("productpriceRequestMarshaller")
	private Jaxb2Marshaller productpriceRequestMarshaller;

	@Autowired
	MessageChannel pcatalogRequest;

	@Autowired
	MessageChannel productPriceOutput;

	public void contactProductCatalog(GetProductPriceRequestType productPriceRequest, MessageHeaders headers) {
		GetProductRequestType pcatalogProductRequest = new GetProductRequestType();
		pcatalogProductRequest.setProductCategory(productPriceRequest.getProductCategory());
		JAXBElement<GetProductRequestType> pcatalogProductRequestPayload = (new ObjectFactory())
				.createGetProductRequest(pcatalogProductRequest);
		invoke(pcatalogProductRequestPayload, productPriceRequest, headers);
	}

	public void invoke(JAXBElement<?> pcatalogProductRequestPayload, GetProductPriceRequestType productPriceRequest,
			MessageHeaders headers) {
		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.putAll(headers);
		headerMap.put("priceRequestProduct", productPriceRequest.getProduct());
		headerMap.put("priceRequestCategory", productPriceRequest.getProductCategory());
		Message<?> pcatalogRequestMsg = MessageBuilder.withPayload(pcatalogProductRequestPayload).copyHeaders(headerMap)
				.build();
		pcatalogRequest.send(pcatalogRequestMsg);
	}

	public void sendProductPriceResponse(Message<?> productPriceResponseMsg) {
		productPriceOutput.send(productPriceResponseMsg);
	}
}
