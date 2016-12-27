package org.nfjs.springws.productprice.util;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.nfjs.springws.pcatalog.domain.request.GetProductRequestType;
import org.nfjs.springws.pcatalog.domain.response.ProductObjectType;
import org.nfjs.springws.productprice.domain.request.GetProductPriceRequestType;
import org.nfjs.springws.productprice.domain.request.ObjectFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.xml.transform.StringResult;

public class PriceServiceUtil {
	public static final String get = "GET";
	public static final String err = "ERROR";
	public static final String errCode = "500";
	public static final String currency = "INR";
	public static final String discountMsg = "Discount applied from product catalog";
	public static final String noDiscountMsg = "Product not eligible for discount";

	public static String createProductPriceRequestPayload(GetProductPriceRequestType productPriceRequest,
			Jaxb2Marshaller productpriceRequestMarshaller, StringResult payloadResult) {
		JAXBElement<GetProductPriceRequestType> productPriceRequestPayload = new ObjectFactory()
				.createGetProductPriceRequest(productPriceRequest);
		productpriceRequestMarshaller.marshal(productPriceRequestPayload, payloadResult);
		return payloadResult.toString();
	}

	public static String createProductCatalogRequestPayload(GetProductRequestType pcatalogRequest,
			Jaxb2Marshaller productcatalogRequestMarshaller, StringResult payloadResult) {
		JAXBElement<GetProductRequestType> pcatalogRequestPayload = new org.nfjs.springws.pcatalog.domain.request.ObjectFactory()
				.createGetProductRequest(pcatalogRequest);
		productcatalogRequestMarshaller.marshal(pcatalogRequestPayload, payloadResult);
		return payloadResult.toString();
	}

	public static JAXBElement<?> unmarshalPCatalogResponse(String pCatalogResponse,
			Jaxb2Marshaller pcatalogResponseMarshaller) {
		Source source = new StreamSource(new StringReader(pCatalogResponse));
		return (JAXBElement<?>) pcatalogResponseMarshaller.unmarshal(source);
	}

	public static BigDecimal getDecimal(String value) {
		return new BigDecimal(value.replaceAll(",", ""));
	}

	public static boolean checkDiscountEligibility(List<ProductObjectType> discountProductsList,
			String priceRequestProduct) {
		for (ProductObjectType productObj : discountProductsList) {
			if (productObj.getProductName().toUpperCase().equals(priceRequestProduct)) {
				return true;
			}
		}
		return false;
	}

	public static String getDiscountMsg(boolean isDiscountEligible, String discountAmount) {
		return isDiscountEligible
				? discountAmount + " " + PriceServiceUtil.currency + " : " + PriceServiceUtil.discountMsg
				: PriceServiceUtil.noDiscountMsg;
	}
}
