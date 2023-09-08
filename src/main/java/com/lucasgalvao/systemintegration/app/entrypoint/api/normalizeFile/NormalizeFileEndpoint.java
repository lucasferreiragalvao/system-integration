package com.lucasgalvao.systemintegration.app.entrypoint.api.normalizeFile;

import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizeFile.response.userorder.UserOrderResponse;
import com.lucasgalvao.systemintegration.app.entrypoint.api.config.bodyparser.MiniBodyParser;
import com.lucasgalvao.systemintegration.util.Transform;
import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;
import com.lucasgalvao.systemintegration.domain.readfile.interactor.ProcessContentFileBufferInteractor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.*;

public class NormalizeFileEndpoint implements HttpHandler {

    private final ProcessContentFileBufferInteractor readFileInteractor;

    public NormalizeFileEndpoint(ProcessContentFileBufferInteractor readFileInteractor){
        this.readFileInteractor = readFileInteractor;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            handlePostNormalizedFiles(MiniBodyParser.transformBodyParser(exchange),exchange);
        } else{
            exchange.sendResponseHeaders(405,0);
            OutputStream os = exchange.getResponseBody();
            os.close();
        }
    }

    public void handlePostNormalizedFiles(Map<String,String> propsRequest, HttpExchange exchange) throws IOException {

        try {
            String[] linesFile = propsRequest.get("file").split("\n");
            List<UserOrderEntity> userOrderEntityList = readFileInteractor.execute(Arrays.asList(linesFile));

            List<UserOrderResponse> userOrderResponse = UserOrderResponse.userOrderEntityToResponse(userOrderEntityList);


            String json = Transform.transformObjToJson(userOrderResponse);

            exchange.sendResponseHeaders(200, json.length());
            OutputStream os = exchange.getResponseBody();
            os.write(json.getBytes());
            os.close();
        }catch (Exception e){
            exchange.sendResponseHeaders(500,0);
            OutputStream os = exchange.getResponseBody();
            os.close();
        }
    }
}
