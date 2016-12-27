package org.nfjs.springws.productprice.core.service;

import java.math.BigDecimal;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.nfjs.springws.productprice.domain.response.DefaultResponseType;
import org.nfjs.springws.productprice.domain.response.GetProductPriceResponseType;
import org.nfjs.springws.productprice.domain.response.ObjectFactory;
import org.nfjs.springws.productprice.util.PriceServiceUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component(value = "pcatalogResponseCreator")
public class ProductPriceResponseCreator {
	Logger lgr = Logger.getLogger(getClass());

	public Message<?> createResponse(Object obj, String operation) {

		return defaultResponse(PriceServiceUtil.err);
	}

	public Message<?> createProductPriceResponse(String product, BigDecimal price, MessageHeaders headers,
			boolean isDiscountEligible, String discountAmount) {
		GetProductPriceResponseType productPriceResponse = new GetProductPriceResponseType();
		productPriceResponse.setProduct(product);
		productPriceResponse.setProductPrice(price);
		productPriceResponse.setCurrency(PriceServiceUtil.currency);
		productPriceResponse
				.setDiscountOfferMessage(PriceServiceUtil.getDiscountMsg(isDiscountEligible, discountAmount));
		JAXBElement<GetProductPriceResponseType> productPriceResponsePayload = (new ObjectFactory())
				.createGetProductPriceResponse(productPriceResponse);
		return MessageBuilder.withPayload(productPriceResponsePayload).copyHeaders(headers).build();

	}

	public Message<?> defaultResponse(String operation) {
		DefaultResponseType defaultResponse = new DefaultResponseType();
		defaultResponse.setStatusCode(PriceServiceUtil.errCode);
		defaultResponse.setStatusMessage(defaultResponse.getStatusMessage() + " - " + operation);
		JAXBElement<?> defaultResponsePayload = (new ObjectFactory()).createDefaultResponse(defaultResponse);
		return MessageBuilder.withPayload(defaultResponsePayload).build();
	}
}
