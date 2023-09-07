package com.lucasgalvao.systemintegration.app.entrypoint.console.file.response.order;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasgalvao.systemintegration.app.entrypoint.console.file.response.product.ProductResponse;
import com.lucasgalvao.systemintegration.app.entrypoint.util.Formatter;
import com.lucasgalvao.systemintegration.domain.readfile.entity.order.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    @JsonProperty("order_id")
    private int orderId;

    @JsonProperty("total")
    private String orderAmount;

    @JsonProperty("date")
    private String dateOrder;

    @JsonProperty("products")
    private List<ProductResponse> products;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public static List<OrderResponse> orderEntityToResponse(List<OrderEntity> listOrderEntity) {

        List<OrderResponse> listOrderResponse = new ArrayList<>();
        for(OrderEntity orderEntity : listOrderEntity) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(orderEntity.getOrderId());
            orderResponse.setOrderAmount(Formatter.decimalFormat("#.00", orderEntity.getOrderAmount(), true));

            orderResponse.setDateOrder(Formatter.dateformat("yyyy-mm-dd",orderEntity.getDateOrder()));
            orderResponse.setProducts(ProductResponse.orderEntityToResponse(orderEntity.getProducts()));
            listOrderResponse.add(orderResponse);
        }

        return listOrderResponse;
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
