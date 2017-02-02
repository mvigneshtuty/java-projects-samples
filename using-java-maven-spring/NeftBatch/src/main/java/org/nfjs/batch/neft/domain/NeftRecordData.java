/*
 * Copyright - StarAlliance GmbH
 */
package org.nfjs.batch.neft.domain;

public class NeftRecordData {
    private String customerId;
    private String customerName;
    private String debitAmount;
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }


}
