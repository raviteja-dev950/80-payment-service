package com.payment.model;

public class Order {
    private String id;
    private String product;
    private int qty;

    public Order() {}

    public Order(String id, String product, int qty) {
        this.id = id;
        this.product = product;
        this.qty = qty;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
}