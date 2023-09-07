package com.lucasgalvao.systemintegration.app.entrypoint.console.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InitialEntrypointTest {

    private InitialEntrypoint initialEntrypoint = new InitialEntrypoint();


    @Test
    @DisplayName("should possible to call initial entrypoint ")
    void callInitialEntrypoint (){

        String input = "teste_1.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InitialEntrypoint initialEntrypointSpy = Mockito.spy(initialEntrypoint);

        initialEntrypointSpy.initialEntrypoint();
        Mockito.verify(initialEntrypointSpy,Mockito.times(1)).requestFile();
    }
}
