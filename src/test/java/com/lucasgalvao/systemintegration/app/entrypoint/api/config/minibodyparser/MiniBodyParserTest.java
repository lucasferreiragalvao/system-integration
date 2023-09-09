package com.lucasgalvao.systemintegration.app.entrypoint.api.config.minibodyparser;

import com.lucasgalvao.systemintegration.app.entrypoint.mock.MockRequest;
import com.lucasgalvao.systemintegration.app.entrypoint.mock.RequestBodyMock;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class MiniBodyParserTest {

    @Test
    @DisplayName("should return map with the request fields when there a file field in request")
    void shouldReturnMapWithRequestFieldsWhenThereFileFieldInRequest () throws IOException {

        HttpExchange mockHttpExchange = Mockito.mock(HttpExchange.class);
        Mockito.when(mockHttpExchange.getRequestBody()).thenReturn(RequestBodyMock.createRequestBodyMock(MockRequest.createMockRequest()));

        MiniBodyParser.transformBodyParser(mockHttpExchange);

        Mockito.verify(mockHttpExchange,Mockito.times(1)).getRequestBody();
    }

    @Test
    @DisplayName("should return map with the request fields when there is no file field in request")
    void shouldReturnMapWithTheRequestFieldsWhenThereNoFileFieldInRequest () throws IOException {

        HttpExchange mockHttpExchange = Mockito.mock(HttpExchange.class);
        Mockito.when(mockHttpExchange.getRequestBody()).thenReturn(RequestBodyMock.createRequestBodyMock(MockRequest.creteMockRequestWithOthersFields()));

        MiniBodyParser miniBodyParser = new MiniBodyParser();
        miniBodyParser.transformBodyParser(mockHttpExchange);

        Mockito.verify(mockHttpExchange,Mockito.times(1)).getRequestBody();
    }
}
