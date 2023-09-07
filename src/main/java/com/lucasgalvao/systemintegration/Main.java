package com.lucasgalvao.systemintegration;

import com.lucasgalvao.systemintegration.app.entrypoint.console.file.InitialEntrypoint;
import com.lucasgalvao.systemintegration.app.provider.file.ReadFileProvider;
import com.lucasgalvao.systemintegration.domain.readfile.usecase.ReadFileUseCase;

import java.io.IOException;
public class Main {

    public static void main(String[] args) throws IOException {

        ReadFileProvider readFileProvider = new ReadFileProvider();
        ReadFileUseCase readFileUseCase = new ReadFileUseCase(readFileProvider);
        InitialEntrypoint initialEntrypoint = new InitialEntrypoint(readFileUseCase);
        initialEntrypoint.initialEntrypoint();
    }
}