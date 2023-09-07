package com.lucasgalvao.systemintegration.domain.readfile.usecase;

import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;
import com.lucasgalvao.systemintegration.domain.readfile.gateway.ReadFileGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReadFileUseCaseTest {

    @InjectMocks
    @Spy
    private ReadFileUseCase readFileUseCase;

    @Mock
    private ReadFileGateway readFileGateway;

    @Test
    @DisplayName("it should be possible to call the execute method of the gateway")
    void callMethodExecuteGateway () throws IOException {

        List<UserOrderEntity> result = readFileUseCase.execute("teste.txt");

        Mockito.verify(readFileGateway,Mockito.times(1)).execute("teste.txt");
        Assertions.assertNull(result);
    }

}
