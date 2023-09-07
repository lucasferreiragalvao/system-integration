package com.lucasgalvao.systemintegration.domain.readfile.entity.file;

import java.util.Date;

public class OrderFileEntity {
    private int idUser;
    private String name;
    private int idOrder;
    private int idProduct;
    private double amountProduct;
    private Date dateOrder;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public double getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(double amountProduct) {
        this.amountProduct = amountProduct;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", idOrder=" + idOrder +
                ", idProduct=" + idProduct +
                ", amountProduct=" + amountProduct +
                ", dateOrder=" + dateOrder +
                '}';
    }
}
