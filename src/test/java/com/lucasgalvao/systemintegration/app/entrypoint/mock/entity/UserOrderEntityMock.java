package com.lucasgalvao.systemintegration.app.entrypoint.mock.entity;

import com.lucasgalvao.systemintegration.domain.readfile.adapter.OrderFileAdapter;
import com.lucasgalvao.systemintegration.domain.readfile.adapter.UserOrderEntityAdapter;
import com.lucasgalvao.systemintegration.domain.readfile.entity.file.OrderFileEntity;
import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;

import java.util.Arrays;
import java.util.List;

public class UserOrderEntityMock {
    public static List<UserOrderEntity> createUserOrderEntityMock(String file){
        List<OrderFileEntity> userOrderEntity = OrderFileAdapter.fileToOrderFileEntity(Arrays.asList(
                file
        ));
        return UserOrderEntityAdapter.orderFileEntityToUserOrderEntity(userOrderEntity);

    }
}
