package com.lucasgalvao.systemintegration.domain.readfile.usecase;

import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;
import com.lucasgalvao.systemintegration.domain.readfile.gateway.ReadFileGateway;
import com.lucasgalvao.systemintegration.domain.readfile.interactor.ReadFileInteractor;

import java.io.FileNotFoundException;
import java.util.List;

public class ReadFileUseCase implements ReadFileInteractor {

    private final ReadFileGateway readFileGateway;

    public ReadFileUseCase(ReadFileGateway readFileGateway){
        this.readFileGateway = readFileGateway;
    }
    @Override
    public List<UserOrderEntity> execute(String dir) throws FileNotFoundException {
        List<String> lines = this.readFileGateway.execute(dir);
        return null;
    }
}
