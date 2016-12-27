package org.nfjs.springws.pcatalog.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.nfjs.springws.pcatalog.core.bean.Product;
import org.nfjs.springws.pcatalog.domain.request.AddProductRequestType;
import org.nfjs.springws.pcatalog.domain.request.DeleteProductRequestType;
import org.nfjs.springws.pcatalog.domain.request.GetProductRequestType;
import org.nfjs.springws.pcatalog.domain.response.AddProductResponseType;
import org.nfjs.springws.pcatalog.domain.response.DefaultResponseType;
import org.nfjs.springws.pcatalog.domain.response.DeleteProductResponseType;
import org.nfjs.springws.pcatalog.domain.response.GetProductResponseType;
import org.nfjs.springws.pcatalog.domain.response.ObjectFactory;
import org.nfjs.springws.pcatalog.domain.response.ProductObjectType;
import org.nfjs.springws.pcatalog.domain.response.ProductsListType;
import org.nfjs.springws.pcatalog.util.PCatalogUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component(value = "pcatalogResponseCreator")
public class PCatalogResponseCreator {
	Logger lgr = Logger.getLogger(getClass());

	public Message<?> createResponse(Object obj, String operation) {
		if (PCatalogUtil.add.equals(operation)) {
			AddProductRequestType request = (AddProductRequestType) obj;
			AddProductResponseType addProductResponse = new AddProductResponseType();
			addProductResponse.setProduct(request.getProduct());
			addProductResponse.setProductCategory(request.getProductCategory());
			addProductResponse.setStatusMessage(request.getProduct() + " added successfully to catalog");
			JAXBElement<?> addProductResponsePayload = (new ObjectFactory())
					.createAddProductResponse(addProductResponse);
			return MessageBuilder.withPayload(addProductResponsePayload).build();
		}
		if (PCatalogUtil.del.equals(operation)) {
			DeleteProductRequestType request = (DeleteProductRequestType) obj;
			DeleteProductResponseType deleteProductResponse = new DeleteProductResponseType();
			deleteProductResponse.setProduct(request.getProduct());
			deleteProductResponse.setStatusMessage(request.getProduct() + " deleted successfully from catalog");
			JAXBElement<?> deleteProductResponsePayload = (new ObjectFactory())
					.createDeleteProductResponse(deleteProductResponse);
			return MessageBuilder.withPayload(deleteProductResponsePayload).build();
		}
		return defaultResponse(PCatalogUtil.err);
	}

	public Message<?> createResponse(Object obj, List<Product> productsList, String operation) {
		if (PCatalogUtil.get.equals(operation)) {
			GetProductRequestType request = (GetProductRequestType) obj;
			GetProductResponseType getProductResponse = new GetProductResponseType();
			getProductResponse.setProductCategory(request.getProductCategory());
			List<ProductObjectType> pObjList = new ArrayList<ProductObjectType>();
			ProductObjectType responseProductObj = null;
			for (Product p : productsList) {
				responseProductObj = new ProductObjectType();
				responseProductObj.setProductName(p.getProduct());
				pObjList.add(responseProductObj);
			}
			ProductsListType pListType = new ProductsListType();
			pListType.getProducts().addAll(pObjList);
			getProductResponse.setProductsList(pListType);
			JAXBElement<?> getProductResponsePayload = (new ObjectFactory())
					.createGetProductResponse(getProductResponse);
			return MessageBuilder.withPayload(getProductResponsePayload).build();
		}
		return defaultResponse(PCatalogUtil.err);
	}

	public Message<?> defaultResponse(String operation) {
		DefaultResponseType defaultResponse = new DefaultResponseType();
		defaultResponse.setStatusCode(PCatalogUtil.errCode);
		defaultResponse.setStatusMessage(defaultResponse.getStatusMessage() + " - " + operation);
		JAXBElement<?> defaultResponsePayload = (new ObjectFactory()).createDefaultResponse(defaultResponse);
		return MessageBuilder.withPayload(defaultResponsePayload).build();
	}
}
