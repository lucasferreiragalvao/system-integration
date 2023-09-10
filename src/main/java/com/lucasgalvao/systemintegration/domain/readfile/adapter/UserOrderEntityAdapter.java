package com.lucasgalvao.systemintegration.domain.readfile.adapter;

import com.lucasgalvao.systemintegration.domain.readfile.entity.file.OrderFileEntity;
import com.lucasgalvao.systemintegration.domain.readfile.entity.order.OrderEntity;
import com.lucasgalvao.systemintegration.domain.readfile.entity.product.ProductEntity;
import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;

import java.util.*;
import java.util.stream.Collectors;

public class UserOrderEntityAdapter {
    public static List<UserOrderEntity> orderFileEntityToUserOrderEntity (List<OrderFileEntity> orderFileEntity){
        List<UserOrderEntity> listUserOrderEntity = new ArrayList<>();

        Map<Integer, String> mapByUser = new HashMap<>();

        for(OrderFileEntity orderfile: orderFileEntity){
            mapByUser.put(orderfile.getIdUser(),orderfile.getName());
        }

        for (Map.Entry<Integer,String> itemOrderFile : mapByUser.entrySet()){
            UserOrderEntity userOrderEntity = new UserOrderEntity();
            userOrderEntity.setUserName(itemOrderFile.getValue());
            userOrderEntity.setUserId(itemOrderFile.getKey());
            userOrderEntity.setOrders(adapterOrderEntity(userOrderEntity.getUserId(),orderFileEntity));
            listUserOrderEntity.add(userOrderEntity);
        }
        return listUserOrderEntity;
    }

    private static List<OrderEntity> adapterOrderEntity(int idUser, List<OrderFileEntity> listOrderFileEntity){

        List<OrderEntity> listOrderEntity = new ArrayList<>();

        Map<Integer, String> mapOrdersByUser = new HashMap<>();
        List<OrderFileEntity> listOrdersFileByUserId = listOrderFileEntity.stream().filter(orderFile -> orderFile.getIdUser() == idUser ).collect(Collectors.toList());

        for(OrderFileEntity orderUserFile: listOrdersFileByUserId){
            mapOrdersByUser.put(orderUserFile.getIdOrder(),orderUserFile.getDateOrder());
        }

        for (Map.Entry<Integer,String> itemOrderFile : mapOrdersByUser.entrySet()){
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(itemOrderFile.getKey());
            orderEntity.setDateOrder(itemOrderFile.getValue());
            orderEntity.setProducts(adapterProductsByOrders(orderEntity,listOrderFileEntity,idUser));
            listOrderEntity.add(orderEntity);
        }

        return  listOrderEntity;
    }

    private  static List<ProductEntity> adapterProductsByOrders(OrderEntity orderEntity, List<OrderFileEntity> listOrderFileEntity, int idUser){
        List<ProductEntity> listProductEntity = new ArrayList<>();

        List<OrderFileEntity> listProductByProduct = listOrderFileEntity.stream().filter(o -> o.getIdOrder() == orderEntity.getOrderId() && o.getIdUser() == idUser).collect(Collectors.toList());


        Map<Integer, Double> mapProductsByOrder = new HashMap<>();

        for (OrderFileEntity productOrder: listProductByProduct){
            if(mapProductsByOrder.get(productOrder.getIdProduct()) != null){
                mapProductsByOrder.put(productOrder.getIdProduct(), (mapProductsByOrder.get(productOrder.getIdProduct())+ productOrder.getAmountProduct()));
            }else {
                mapProductsByOrder.put(productOrder.getIdProduct(), productOrder.getAmountProduct());
            }
        }

        orderEntity.setOrderAmount(mapProductsByOrder.values().stream().mapToDouble(value -> value).sum());

        for (Map.Entry<Integer,Double> productByOrder : mapProductsByOrder.entrySet()) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductId(productByOrder.getKey());
            productEntity.setAmountProduct(productByOrder.getValue());
            listProductEntity.add(productEntity);
        }

        return  listProductEntity;
    }
}
