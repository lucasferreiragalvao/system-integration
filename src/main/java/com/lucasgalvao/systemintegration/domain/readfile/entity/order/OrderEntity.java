package com.lucasgalvao.systemintegration.domain.readfile.entity.order;

import com.lucasgalvao.systemintegration.domain.readfile.entity.product.ProductEntity;

import java.util.Date;
import java.util.List;

public class OrderEntity {
    private int orderId;
    private double orderAmount;
    private String dateOrder;

    private List<ProductEntity> products;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", orderAmount=" + orderAmount +
                ", dateOrder=" + dateOrder +
                ", products=" + products +
                '}';
    }
}
