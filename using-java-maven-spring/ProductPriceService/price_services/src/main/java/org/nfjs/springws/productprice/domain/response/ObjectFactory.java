//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.06 at 09:36:41 PM IST 
//


package org.nfjs.springws.productprice.domain.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.nfjs.springws.productprice.domain.response package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetProductPriceResponse_QNAME = new QName("http://org.nfjs/springws/productprice/response", "GetProductPriceResponse");
    private final static QName _DefaultResponse_QNAME = new QName("http://org.nfjs/springws/productprice/response", "DefaultResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.nfjs.springws.productprice.domain.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductPriceResponseType }
     * 
     */
    public GetProductPriceResponseType createGetProductPriceResponseType() {
        return new GetProductPriceResponseType();
    }

    /**
     * Create an instance of {@link DefaultResponseType }
     * 
     */
    public DefaultResponseType createDefaultResponseType() {
        return new DefaultResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductPriceResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://org.nfjs/springws/productprice/response", name = "GetProductPriceResponse")
    public JAXBElement<GetProductPriceResponseType> createGetProductPriceResponse(GetProductPriceResponseType value) {
        return new JAXBElement<GetProductPriceResponseType>(_GetProductPriceResponse_QNAME, GetProductPriceResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefaultResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://org.nfjs/springws/productprice/response", name = "DefaultResponse")
    public JAXBElement<DefaultResponseType> createDefaultResponse(DefaultResponseType value) {
        return new JAXBElement<DefaultResponseType>(_DefaultResponse_QNAME, DefaultResponseType.class, null, value);
    }

}
