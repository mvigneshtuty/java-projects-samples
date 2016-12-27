package org.nfjs.springws.pcatalog.core.service;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.nfjs.springws.pcatalog.core.bean.Product;
import org.nfjs.springws.pcatalog.core.repository.ProductRepository;
import org.nfjs.springws.pcatalog.domain.request.AddProductRequestType;
import org.nfjs.springws.pcatalog.domain.request.DeleteProductRequestType;
import org.nfjs.springws.pcatalog.domain.request.GetProductRequestType;
import org.nfjs.springws.pcatalog.util.PCatalogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component(value = "pcatalogHandler")
public class PCatalogMessageHandler {
	Logger lgr = Logger.getLogger(getClass());

	@Autowired
	ProductRepository pcatalogRepository;

	@Autowired
	PCatalogResponseCreator pcatalogResponseCreator;

	@SuppressWarnings("unchecked")
	@ServiceActivator
	public Message<JAXBElement<?>> handlePCatalogRequest(Message<JAXBElement<?>> message) {
		if (message.getPayload().getValue() instanceof GetProductRequestType) {
			// Request to Get Existing Product;
			GetProductRequestType getProductObj = (GetProductRequestType) message.getPayload().getValue();
			List<Product> productsList = pcatalogRepository.getProduct(getProductObj);
			return (Message<JAXBElement<?>>) pcatalogResponseCreator.createResponse(getProductObj, productsList,
					PCatalogUtil.get);
		}
		if (message.getPayload().getValue() instanceof AddProductRequestType) {
			// Request to Add New Product;
			AddProductRequestType newProductObj = (AddProductRequestType) message.getPayload().getValue();
			int addStatus = pcatalogRepository.addNewProduct(newProductObj);
			if (!(addStatus > 0)) {
				return (Message<JAXBElement<?>>) pcatalogResponseCreator.defaultResponse(PCatalogUtil.add);
			}
			return (Message<JAXBElement<?>>) pcatalogResponseCreator.createResponse(newProductObj, PCatalogUtil.add);
		}
		if (message.getPayload().getValue() instanceof DeleteProductRequestType) {
			// Request to Delete Existing Product;
			DeleteProductRequestType deleteProductObj = (DeleteProductRequestType) message.getPayload().getValue();
			int deleteStatus = pcatalogRepository.deleteProduct(deleteProductObj);
			if (!(deleteStatus > 0)) {
				return (Message<JAXBElement<?>>) pcatalogResponseCreator.defaultResponse(PCatalogUtil.del);
			}
			return (Message<JAXBElement<?>>) pcatalogResponseCreator.createResponse(deleteProductObj, PCatalogUtil.del);
		}
		return (Message<JAXBElement<?>>) pcatalogResponseCreator.defaultResponse(PCatalogUtil.err);
	}
}
