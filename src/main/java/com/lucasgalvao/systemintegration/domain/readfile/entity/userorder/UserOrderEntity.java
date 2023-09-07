package com.lucasgalvao.systemintegration.domain.readfile.entity.userorder;

import com.lucasgalvao.systemintegration.domain.readfile.entity.order.OrderEntity;

import java.util.List;

public class UserOrderEntity {
    private int userId;
    private String userName;

    private List<OrderEntity> orders;

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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "UserOrderEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", orders=" + orders +
                '}';
    }
}
