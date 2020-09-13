package com.putrasamudra.kafein.model;

public class Invoice {
    private String datelocation;
    private String name;
    private String noinvoice;
    private String payment;
    private String price;
    private String status;

    public String getDatelocation() {
        return datelocation;
    }

    public void setDatelocation(String datelocation) {
        this.datelocation = datelocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoinvoice() {
        return noinvoice;
    }

    public void setNoinvoice(String noinvoice) {
        this.noinvoice = noinvoice;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
