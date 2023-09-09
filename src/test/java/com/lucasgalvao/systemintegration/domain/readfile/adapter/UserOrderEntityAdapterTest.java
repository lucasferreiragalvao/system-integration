package com.lucasgalvao.systemintegration.domain.readfile.adapter;

import com.lucasgalvao.systemintegration.domain.readfile.entity.file.OrderFileEntity;
import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserOrderEntityAdapterTest {

    @Test
    @DisplayName("should return listUserOrderEntity when the file has only one line")
    void shouldReturnListUserOrderEntityWhenFileHasOnlyOneLine ()  {
        String file = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";

        List<OrderFileEntity>  listOrderFileEntity = OrderFileAdapter.fileToOrderFileEntity(Arrays.asList(file));

        List<UserOrderEntity> listUserOrderEntity = UserOrderEntityAdapter.orderFileEntityToUserOrderEntity(listOrderFileEntity);

        Assertions.assertEquals(listUserOrderEntity.size(),1);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().size(), 1);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().get(0).getProducts().size(),1);

    }

    @Test
    @DisplayName("should return listUserOrderEntity when the file has orders from different users")
    void shouldReturnListUserOrderEntityWhenFileHasRequestsFromDifferentUsers ()  {
        String file = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308\n"+
                      "0000000071                              Testes Testando00000007630000000003     1000.0020210422";

        List<OrderFileEntity>  listOrderFileEntity = OrderFileAdapter.fileToOrderFileEntity(Arrays.asList(file.split("\n")));

        List<UserOrderEntity> listUserOrderEntity = UserOrderEntityAdapter.orderFileEntityToUserOrderEntity(listOrderFileEntity);

        Assertions.assertEquals(listUserOrderEntity.size(),2);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().size(), 1);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().get(0).getProducts().size(),1);

    }

    @Test
    @DisplayName("should return listUserOrderEntity when the file has different orders from the same user")
    void shouldReturnListUserOrderEntityWhenFileHasDifferentOrdersFromSameUser ()  {
        String file = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308\n"+
                      "0000000070                              Palmer Prosacco00000007610000000003     1000.0020210422";

        List<OrderFileEntity>  listOrderFileEntity = OrderFileAdapter.fileToOrderFileEntity(Arrays.asList(file.split("\n")));

        List<UserOrderEntity> listUserOrderEntity = UserOrderEntityAdapter.orderFileEntityToUserOrderEntity(listOrderFileEntity);

        Assertions.assertEquals(listUserOrderEntity.size(),1);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().size(), 2);

    }

    @Test
    @DisplayName("should return listUserOrderEntity when the file has lines from the same user and the same order but they are different products")
    void shouldReturnListUserOrderEntityWhenFileHasLinesFromTheSameUserAndTheSameOrderButTheyAreDifferentProducts ()  {
        String file = "0000000070                              Palmer Prosacco00000007610000000003     1836.7420210308\n"+
                      "0000000070                              Palmer Prosacco00000007610000000004     1000.0020210422";

        List<OrderFileEntity>  listOrderFileEntity = OrderFileAdapter.fileToOrderFileEntity(Arrays.asList(file.split("\n")));

        List<UserOrderEntity> listUserOrderEntity = UserOrderEntityAdapter.orderFileEntityToUserOrderEntity(listOrderFileEntity);

        Assertions.assertEquals(listUserOrderEntity.size(),1);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().size(), 1);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().get(0).getProducts().size(),2);

    }

    @Test
    @DisplayName("should return listUserOrderEntity when the file has lines from the same user and the same order and same products")
    void shouldReturnListUserOrderEntityWhenTheFileHasLinesFromTheSameUserAndTheSameOrderAndSameProducts ()  {
        String file = "0000000070                              Palmer Prosacco00000007610000000003     1836.7420210308\n"+
                      "0000000070                              Palmer Prosacco00000007610000000003     1000.0020210422";

        List<OrderFileEntity>  listOrderFileEntity = OrderFileAdapter.fileToOrderFileEntity(Arrays.asList(file.split("\n")));

        UserOrderEntityAdapter userOrderEntityAdapter = new UserOrderEntityAdapter();

        List<UserOrderEntity> listUserOrderEntity = userOrderEntityAdapter.orderFileEntityToUserOrderEntity(listOrderFileEntity);

        Assertions.assertEquals(listUserOrderEntity.size(),1);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().size(), 1);
        Assertions.assertEquals(listUserOrderEntity.get(0).getOrders().get(0).getProducts().size(),1);

    }


}
