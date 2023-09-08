package com.lucasgalvao.systemintegration.domain.readfile.usecase;

import com.lucasgalvao.systemintegration.domain.readfile.adapter.OrderFileAdapter;
import com.lucasgalvao.systemintegration.domain.readfile.adapter.UserOrderEntityAdapter;
import com.lucasgalvao.systemintegration.domain.readfile.entity.file.OrderFileEntity;
import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;
import com.lucasgalvao.systemintegration.domain.readfile.gateway.ReadFileGateway;
import com.lucasgalvao.systemintegration.domain.readfile.interactor.ProcessContentFileBufferInteractor;

import java.io.FileNotFoundException;
import java.util.List;

public class ProcessContentFileBufferUseCase implements ProcessContentFileBufferInteractor {
    @Override
    public List<UserOrderEntity> execute(List<String> buffer) throws FileNotFoundException {
        List<OrderFileEntity> orderFileEntiy = OrderFileAdapter.fileToOrderFileEntity(buffer);
        return UserOrderEntityAdapter.orderFileEntityToUserOrderEntity(orderFileEntiy);
    }
}
