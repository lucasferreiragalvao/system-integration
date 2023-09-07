package com.lucasgalvao.systemintegration.app.provider.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;

@ExtendWith(MockitoExtension.class)
public class ReadFileProviderTest {
    @InjectMocks
    @Spy
    private ReadFileProvider readFileProvider;


    @Test
    @DisplayName("it should be possible to call the execute method of the gateway")
    void callMethodExecuteGateway () throws IOException {

        String input = "0000000001                                        Teste00000000010000000001     1836.7420230907";


        readFileProvider.execute("teste");

        /*Mockito.verify(readFileProvider,Mockito.times(1)).execute("teste.txt");*/
    }
}
