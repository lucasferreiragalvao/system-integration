package com.lucasgalvao.systemintegration.app.entrypoint.api.config.routes;

import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizeFile.NormalizeFileEndpoint;
import com.lucasgalvao.systemintegration.domain.readfile.usecase.ProcessContentFileBufferUseCase;
import com.sun.net.httpserver.HttpServer;

public class Routes {
    public Routes(HttpServer server){
        ProcessContentFileBufferUseCase readFileUseCase = new ProcessContentFileBufferUseCase();
        server.createContext("/normalize-file", new NormalizeFileEndpoint(readFileUseCase));
    }
}