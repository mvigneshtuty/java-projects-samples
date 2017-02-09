/*
 * Copyright (c) codehowpedia. All Rights Reserved.
 * ============================================================
 */
package org.codehowpedia.nfjs.springjaxb.domain;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderRequest")
// @XmlAccessorType(XmlAccessType.FIELD)
public class OrderRequest {


    private String retailerId;

    private ArrayList<Order> orderDetails;

    @XmlElement(name = "RetailerId")
    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    @XmlElement(name = "OrderDetails")
    public ArrayList<Order> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<Order> orderDetails) {
        this.orderDetails = orderDetails;
    }

    /*@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((retailerId == null) ? 0 : retailerId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderRequest other = (OrderRequest) obj;
        if (retailerId == null) {
            if (other.retailerId != null)
                return false;
        }
        else if (!retailerId.equals(other.retailerId))
            return false;
        return true;
    }*/

}
