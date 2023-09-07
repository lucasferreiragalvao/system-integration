package com.lucasgalvao.systemintegration.domain.readfile.usecase;

import com.lucasgalvao.systemintegration.domain.readfile.gateway.ExportFileGateway;
import com.lucasgalvao.systemintegration.domain.readfile.interactor.ExportFileInteractor;

import java.io.IOException;

public class ExportFileUseCase implements ExportFileInteractor {

    private final ExportFileGateway exportFileGateway;

    public ExportFileUseCase(ExportFileGateway exportFileGateway){
        this.exportFileGateway = exportFileGateway;
    }
    @Override
    public String execute(String data) throws IOException {
        return this.exportFileGateway.execute(data);
    }
}
