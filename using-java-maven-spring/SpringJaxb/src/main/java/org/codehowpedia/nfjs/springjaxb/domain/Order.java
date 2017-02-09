/*
 * Copyright (c) codehowpedia. All Rights Reserved.
 * ============================================================
 */
package org.codehowpedia.nfjs.springjaxb.domain;


//@XmlRootElement(name = "Order")
// @XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    public Order() {
        // TODO Auto-generated constructor block
    }

    private String orderId;
    private String customerId;
    private String retailerId;
    private String bookId;
    private int quantity;
    private int pendingForDelivery;
    private String status;

    public Order(String orderId, String customerId, String retailerId, String bookId, int quantity, int pendingForDelivery, String status) {
        super();
        this.orderId = orderId;
        this.customerId = customerId;
        this.retailerId = retailerId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.pendingForDelivery = pendingForDelivery;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPendingForDelivery() {
        return pendingForDelivery;
    }

    public void setPendingForDelivery(int pendingForDelivery) {
        this.pendingForDelivery = pendingForDelivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
