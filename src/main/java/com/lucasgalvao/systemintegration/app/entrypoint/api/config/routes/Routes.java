package com.lucasgalvao.systemintegration.app.entrypoint.api.config.routes;

import com.lucasgalvao.systemintegration.app.entrypoint.api.config.middleware.minibodyparser.MiddlewareBodyParser;
import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.NormalizeFileEndpoint;
import com.lucasgalvao.systemintegration.domain.readfile.usecase.ProcessContentFileBufferUseCase;
import com.sun.net.httpserver.HttpServer;

public class Routes {
    public Routes(HttpServer server){
        ProcessContentFileBufferUseCase readFileUseCase = new ProcessContentFileBufferUseCase();

        server.createContext("/api/normalize-file", new MiddlewareBodyParser(new NormalizeFileEndpoint(readFileUseCase)));
    }
}
