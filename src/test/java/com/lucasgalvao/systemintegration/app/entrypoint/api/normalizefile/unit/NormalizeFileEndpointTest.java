package com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.unit;

import com.lucasgalvao.systemintegration.app.entrypoint.api.normalizefile.NormalizeFileEndpoint;
import com.lucasgalvao.systemintegration.app.entrypoint.mock.MockRequest;
import com.lucasgalvao.systemintegration.app.entrypoint.mock.OutputStreamMock;
import com.lucasgalvao.systemintegration.app.entrypoint.mock.RequestBodyMock;
import com.lucasgalvao.systemintegration.app.entrypoint.mock.entity.UserOrderEntityMock;
import com.lucasgalvao.systemintegration.domain.readfile.interactor.ProcessContentFileBufferInteractor;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@ExtendWith(MockitoExtension.class)
public class NormalizeFileEndpointTest {

    @InjectMocks
    private NormalizeFileEndpoint normalizeFileEndpoint;

    @Mock
    ProcessContentFileBufferInteractor processContentFileBufferInteractor;

    @Test
    @DisplayName("should return status 405 when method is not available")
    void shouldReturnStatus405WhenMethodNotAvailable () throws IOException {

        HttpExchange mockHttpExchange = Mockito.mock(HttpExchange.class);
        Mockito.when(mockHttpExchange.getRequestMethod()).thenReturn("GET");
        Mockito.when(mockHttpExchange.getResponseBody()).thenReturn(OutputStreamMock.createOutputStream());

        normalizeFileEndpoint.handle(mockHttpExchange);

        Mockito.verify(mockHttpExchange,Mockito.times(1)).sendResponseHeaders(405,0);
        Mockito.verify(mockHttpExchange,Mockito.times(1)).getResponseBody();
    }

    @Test
    @DisplayName("should return status 200 when method is available")
    void shouldReturnStatus200WhenMethodAvailable () throws IOException {

        String file = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        HttpExchange mockHttpExchange = Mockito.mock(HttpExchange.class);
        Mockito.when(mockHttpExchange.getRequestMethod()).thenReturn("POST");
        Mockito.when(mockHttpExchange.getResponseBody()).thenReturn(OutputStreamMock.createOutputStream());
        Mockito.when(mockHttpExchange.getRequestBody()).thenReturn(RequestBodyMock.createRequestBodyMock(MockRequest.createMockRequest()));
        Mockito.when(processContentFileBufferInteractor.execute(Mockito.anyList())).thenReturn(UserOrderEntityMock.createUserOrderEntityMock(file));

        normalizeFileEndpoint.handle(mockHttpExchange);


        Mockito.verify(processContentFileBufferInteractor,Mockito.times(1)).execute(Mockito.any());
        Mockito.verify(mockHttpExchange,Mockito.times(1)).sendResponseHeaders(200, 231);
        Mockito.verify(mockHttpExchange,Mockito.times(1)).getResponseBody();

    }

    @Test
    @DisplayName("should return status 400 when method return exception")
    void shouldReturnStatus500WhenMethodReturnExepction () throws IOException {

        InputStream mock = new ByteArrayInputStream("".getBytes());

        OutputStream mockOutput = Mockito.mock(OutputStream.class);

        HttpExchange mockHttpExchange = Mockito.mock(HttpExchange.class);
        Mockito.when(mockHttpExchange.getRequestMethod()).thenReturn("POST");
        Mockito.when(mockHttpExchange.getResponseBody()).thenReturn(mockOutput);
        Mockito.when(mockHttpExchange.getRequestBody()).thenReturn(mock);

        normalizeFileEndpoint.handle(mockHttpExchange);


        Mockito.verify(processContentFileBufferInteractor,Mockito.times(0)).execute(Mockito.any());
        Mockito.verify(mockHttpExchange,Mockito.times(1)).sendResponseHeaders(400, 0L);
        Mockito.verify(mockHttpExchange,Mockito.times(1)).getResponseBody();

    }
}
