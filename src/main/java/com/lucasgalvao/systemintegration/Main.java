package com.lucasgalvao.systemintegration;

import com.lucasgalvao.systemintegration.app.entrypoint.console.file.InitialEntrypoint;
import com.lucasgalvao.systemintegration.app.provider.file.ExportFileProvider;
import com.lucasgalvao.systemintegration.app.provider.file.ReadFileProvider;
import com.lucasgalvao.systemintegration.domain.readfile.usecase.ExportFileUseCase;
import com.lucasgalvao.systemintegration.domain.readfile.usecase.ReadFileUseCase;

public class Main {

    public static void main(String[] args) {

        ReadFileProvider readFileProvider = new ReadFileProvider();
        ExportFileProvider exportFileProvider = new ExportFileProvider();

        ReadFileUseCase readFileUseCase = new ReadFileUseCase(readFileProvider);
        ExportFileUseCase exportFileUseCase = new ExportFileUseCase(exportFileProvider);

        InitialEntrypoint initialEntrypoint = new InitialEntrypoint(readFileUseCase,exportFileUseCase);
        initialEntrypoint.initialEntrypoint();
    }
}