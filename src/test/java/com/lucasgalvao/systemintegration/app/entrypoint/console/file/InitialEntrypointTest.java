package com.lucasgalvao.systemintegration.app.entrypoint.console.file;

import com.lucasgalvao.systemintegration.domain.readfile.interactor.ReadFileInteractor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
public class InitialEntrypointTest {

    @Mock
    private ReadFileInteractor readFileInteractor;
    @InjectMocks
    @Spy
    private InitialEntrypoint initialEntrypoint;

    @Test
    @DisplayName("should possible to call initial entrypoint ")
    void callInitialEntrypoint () throws IOException {

        String input = "teste_1.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        initialEntrypoint.initialEntrypoint();
        Mockito.verify(initialEntrypoint,Mockito.times(1)).requestFile();
        Mockito.verify(readFileInteractor,Mockito.times(1)).execute(input);
    }
    @Test()
    @DisplayName("should possible to call initial entrypoint ")
    void callInitialEntrypointWithError () throws IOException {

        String input = "file_not_found.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Mockito.when(this.readFileInteractor.execute(Mockito.anyString())).thenThrow(FileNotFoundException.class);

        initialEntrypoint.initialEntrypoint();

        Mockito.verify(initialEntrypoint,Mockito.times(1)).requestFile();
        Mockito.verify(readFileInteractor,Mockito.times(1)).execute(input);

    }

}
