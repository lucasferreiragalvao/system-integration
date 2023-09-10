package com.lucasgalvao.systemintegration.app.entrypoint.api.config.middleware.minibodyparser;

import com.lucasgalvao.systemintegration.app.entrypoint.api.config.middleware.minibodyparser.MiniBodyParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class MiddlewareBodyParser implements HttpHandler {

    private final HttpHandler mainHandler;

    public MiddlewareBodyParser(HttpHandler mainHandler) {
        this.mainHandler = mainHandler;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.setAttribute("params", MiniBodyParser.transformBodyParser(exchange));
        mainHandler.handle(exchange);
    }
}
