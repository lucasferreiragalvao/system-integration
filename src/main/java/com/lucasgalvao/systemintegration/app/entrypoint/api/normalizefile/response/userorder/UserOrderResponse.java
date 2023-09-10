package com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.response.userorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.response.order.OrderResponse;
import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;

import java.util.ArrayList;
import java.util.List;

public class UserOrderResponse {
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("name")
    private String userName;

    @JsonProperty("orders")
    private List<OrderResponse> orders;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponse> orders) {
        this.orders = orders;
    }

    public static List<UserOrderResponse> userOrderEntityToResponse(List<UserOrderEntity> listUserOrderEntity) {

        List<UserOrderResponse> listUserOrderResponse = new ArrayList<>();
        for(UserOrderEntity userOrderEntity : listUserOrderEntity) {
            UserOrderResponse userOrderResponse = new UserOrderResponse();
            userOrderResponse.setUserId(userOrderEntity.getUserId());
            userOrderResponse.setUserName(userOrderEntity.getUserName());
            userOrderResponse.setOrders(OrderResponse.orderEntityToResponse(userOrderEntity.getOrders()));
            listUserOrderResponse.add(userOrderResponse);
        }

        return listUserOrderResponse;
    }
}
