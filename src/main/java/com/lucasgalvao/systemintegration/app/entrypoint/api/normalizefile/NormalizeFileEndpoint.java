package com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile;

import com.lucasgalvao.systemintegration.app.entrypoint.api.config.middleware.minibodyparser.props.Props;
import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.response.userorder.UserOrderResponse;
import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.validate.ErrorValidateNormalizeFile;
import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.validate.ValidateNormalizeFile;
import com.lucasgalvao.systemintegration.util.Transform;
import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;
import com.lucasgalvao.systemintegration.domain.readfile.interactor.ProcessContentFileBufferInteractor;
import com.lucasgalvao.systemintegration.util.response.BadRequestResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.hc.core5.http.HttpStatus;

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
            Map<String,Props> propsRequest = (Map<String, Props>) exchange.getAttribute("params");
            handlePostNormalizedFiles(propsRequest,exchange);
        } else{
            exchange.sendResponseHeaders(405,0);
            OutputStream os = exchange.getResponseBody();
            os.close();
        }
    }

    public void handlePostNormalizedFiles(Map<String, Props> propsRequest, HttpExchange exchange) throws IOException {

        try {
            ValidateNormalizeFile.validateNormalizeField(exchange, propsRequest);
            String[] linesFile = propsRequest.get("file").getValue().split("\n");
            List<UserOrderEntity> userOrderEntityList = readFileInteractor.execute(Arrays.asList(linesFile));

            List<UserOrderResponse> userOrderResponse = UserOrderResponse.userOrderEntityToResponse(userOrderEntityList);


            String json = Transform.transformObjToJson(userOrderResponse);

            exchange.sendResponseHeaders(200, json.length());
            OutputStream os = exchange.getResponseBody();
            os.write(json.getBytes());
            os.close();
        } catch (Exception e){
            OutputStream os = exchange.getResponseBody();
            if(e instanceof ErrorValidateNormalizeFile){
                exchange.sendResponseHeaders(HttpStatus.SC_BAD_REQUEST, 0);
                BadRequestResponse badRequestResponse = new BadRequestResponse();
                badRequestResponse.setMessage(e.getMessage());
                os.write(Transform.transformObjToJson(badRequestResponse).getBytes());
            }else {
                exchange.sendResponseHeaders(500, 0);
            }
            os.close();
        }
    }
}
