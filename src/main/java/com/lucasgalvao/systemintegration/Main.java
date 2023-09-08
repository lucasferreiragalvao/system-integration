package com.lucasgalvao.systemintegration;

import com.lucasgalvao.systemintegration.app.entrypoint.api.config.routes.Routes;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        new Routes(server);
        server.setExecutor(null);
        server.start();

    }
}