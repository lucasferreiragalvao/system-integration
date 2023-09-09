package com.lucasgalvao.systemintegration.domain.readfile.adapter;

import com.lucasgalvao.systemintegration.domain.readfile.entity.file.OrderFileEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderFileAdapterTest {
    @Test
    @DisplayName("should return listOrderEntity when the file has only one line")
    void shouldReturnListUserOrderEntityWhenFileHasOnlyOneLine ()  {
        String file = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";

        List<OrderFileEntity> listOrderFileEntity = OrderFileAdapter.fileToOrderFileEntity(Arrays.asList(file.split("\n")));

        Assertions.assertEquals(listOrderFileEntity.size(),1);
        Assertions.assertEquals(listOrderFileEntity.get(0).getIdOrder(),753);

    }
}
