package com.lucasgalvao.systemintegration.domain.readfile.usecase;

import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProcessContenteFileBufferUseCaseTest {

    @InjectMocks
    @Spy
    private ProcessContentFileBufferUseCase processContentFileBufferUseCase;

    @Test
    @DisplayName("should return userOrderEntity when buffer list is as expected")
    void shouldReturnUserOrderEntityWhenBufferListIsExpected ()  {
        List<UserOrderEntity> result = processContentFileBufferUseCase.execute(Arrays.asList("0000000088                             Terra Daniel DDS00000008360000000003     1899.0220210909"));

        Assertions.assertEquals(result.size(),1);
        Assertions.assertEquals(result.get(0).getClass(),UserOrderEntity.class);
        Assertions.assertEquals(result.get(0).getOrders().get(0).getOrderAmount(), 1899.02);
        Assertions.assertEquals(result.get(0).getUserId(),88);
        Assertions.assertEquals(result.get(0).getUserName(),"Terra Daniel DDS");
        Assertions.assertEquals(result.get(0).getOrders().get(0).getProducts().get(0).getProductId(), 3);
        Assertions.assertEquals(result.get(0).getOrders().get(0).getProducts().get(0).getAmountProduct(), 1899.02);
    }

    @Test
    @DisplayName("should return error when buffer list is not as expected")
    void shouldReturnErrorWhenBufferListIsNotExpected ()  {
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> processContentFileBufferUseCase.execute(Arrays.asList("")));
    }

}
