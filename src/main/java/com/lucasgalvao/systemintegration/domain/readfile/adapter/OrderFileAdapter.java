package com.lucasgalvao.systemintegration.domain.readfile.adapter;

import com.lucasgalvao.systemintegration.domain.readfile.entity.file.OrderFileEntity;
import com.lucasgalvao.systemintegration.util.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFileAdapter {

    public static List<OrderFileEntity> fileToOrderFileEntity(List<String> linesFile) {
        List<OrderFileEntity> orders = new ArrayList<>();
        linesFile.forEach(line -> {
            OrderFileEntity order = new OrderFileEntity();
            order.setIdUser(Integer.parseInt(line.substring(0, 10)));
            order.setName(line.substring(10, 55).trim());
            order.setIdOrder(Integer.parseInt(line.substring(55, 65)));
            order.setIdProduct(Integer.parseInt(line.substring(65, 75)));
            order.setAmountProduct(Double.parseDouble(line.substring(75, 87)));

            order.setDateOrder(Formatter.dateformat("yyyy-mm-dd", dateOrderFile(line.substring(87,95))));
            orders.add(order);
        });
        return orders;
    }

    private static Date dateOrderFile(String data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
        Date date;
        try {
            date = format.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
