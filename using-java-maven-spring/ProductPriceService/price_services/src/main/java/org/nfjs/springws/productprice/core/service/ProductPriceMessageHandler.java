package org.nfjs.springws.productprice.core.service;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.nfjs.springws.pcatalog.domain.request.GetProductRequestType;
import org.nfjs.springws.pcatalog.domain.response.GetProductResponseType;
import org.nfjs.springws.pcatalog.domain.response.ProductObjectType;
import org.nfjs.springws.productprice.domain.request.GetProductPriceRequestType;
import org.nfjs.springws.productprice.util.PriceServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringResult;

@Component(value = "productPriceHandler")
public class ProductPriceMessageHandler {
	Logger lgr = Logger.getLogger(getClass());

	@Autowired
	ProductPriceResponseCreator productpriceResponseCreator;

	@Autowired
	ProductPriceMessageSender messageSender;

	@Autowired
	@Qualifier("productcatalogRequestMarshaller")
	Jaxb2Marshaller productcatalogRequestMarshaller;

	@Autowired
	@Qualifier("productcatalogResponseMarshaller")
	Jaxb2Marshaller productcatalogResponseMarshaller;

	@Value("${price.default}")
	private String defaultPrice;

	@Value("${amount.discount}")
	private String discountAmount;

	boolean isDiscountEligible;

	public void handleProductPriceRequest(Message<JAXBElement<?>> message) {
		GetProductPriceRequestType request = (GetProductPriceRequestType) message.getPayload().getValue();
		messageSender.contactProductCatalog(request, message.getHeaders());
	}

	public void handlePCatalogResponse(Message<?> message) {
		JAXBElement<?> pcatalogResponsePayload = PriceServiceUtil
				.unmarshalPCatalogResponse((String) message.getPayload(), productcatalogResponseMarshaller);
		GetProductResponseType pcatalogResponse = (GetProductResponseType) pcatalogResponsePayload.getValue();
		List<ProductObjectType> discountProductsList = pcatalogResponse.getProductsList().getProducts();
		String priceRequestProduct = ((String) message.getHeaders().get("priceRequestProduct"));
		isDiscountEligible = PriceServiceUtil.checkDiscountEligibility(discountProductsList,
				priceRequestProduct.toUpperCase());
		Message<?> productPriceResponseMsg = productpriceResponseCreator.createProductPriceResponse(priceRequestProduct,
				calcPrice(isDiscountEligible), message.getHeaders(), isDiscountEligible, discountAmount);
		messageSender.sendProductPriceResponse(productPriceResponseMsg);
	}

	public BigDecimal calcPrice(boolean isDiscountEligible) {
		return isDiscountEligible ? discountPrice() : PriceServiceUtil.getDecimal(defaultPrice);
	}

	public BigDecimal discountPrice() {
		return PriceServiceUtil.getDecimal(defaultPrice).subtract(PriceServiceUtil.getDecimal(discountAmount));
	}

	@Transformer
	public String transformProductCatalogRequest(Message<JAXBElement<?>> message) {
		GetProductRequestType pcatalogRequest = (GetProductRequestType) message.getPayload().getValue();
		return PriceServiceUtil.createProductCatalogRequestPayload(pcatalogRequest, productcatalogRequestMarshaller,
				new StringResult());
	}
}
