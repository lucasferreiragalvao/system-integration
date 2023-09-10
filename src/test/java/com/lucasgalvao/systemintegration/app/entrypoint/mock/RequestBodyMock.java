package com.lucasgalvao.systemintegration.app.entrypoint.mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class RequestBodyMock {
    public static InputStream createRequestBodyMock(String request){
        InputStream mock = new ByteArrayInputStream(request.getBytes());

        return mock;
    }
}
