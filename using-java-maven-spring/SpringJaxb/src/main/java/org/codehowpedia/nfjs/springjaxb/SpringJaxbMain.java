/*
 * Copyright (c) Star Alliance. All Rights Reserved.
 * ============================================================
 */
package org.codehowpedia.nfjs.springjaxb;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.codehowpedia.nfjs.springjaxb.domain.Order;
import org.codehowpedia.nfjs.springjaxb.domain.OrderRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

public class SpringJaxbMain {

    public static void main(String[] args) {
        String cfg = "spring-jaxb-config.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(cfg);
        Marshaller marshaller = (Marshaller) ctx.getBean("jaxbMarshallerBean");
        OrderRequest ordReq = new OrderRequest();
        ordReq.setRetailerId("WSRetail");

        ArrayList<Order> ordDetails = new ArrayList<Order>();
        ordDetails.add(new Order("1", "CS011", "WSRetail", "SIA", 2, 2, "ORD-PLAC"));
        ordDetails.add(new Order("2", "353453", "Vignesh", "HIA", 1, 1, "ORD-PLAC"));
        ordReq.setOrderDetails(ordDetails);

        try {
            marshaller.marshal(ordReq, new StreamResult(new FileWriter("D:\\VICKY\\works\\spring\\spring-play-ground\\order-request-4.xml")));
        }
        catch (XmlMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("marshalling success!");

        Unmarshaller unmarshaller = (Unmarshaller) ctx.getBean("jaxbMarshallerBean");
        OrderRequest ordReqJaxb = null;
        try {
            ordReqJaxb = (OrderRequest) unmarshaller.unmarshal(new StreamSource(new FileInputStream("D:\\VICKY\\works\\spring\\spring-play-ground\\order-request-4.xml")));
            System.out.println("unmarshalled retailer : " + ordReqJaxb.getRetailerId());
        }
        catch (XmlMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("unmarshalling success!");

    }

}
