package com.lucasgalvao.systemintegration.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;


@ExtendWith(MockitoExtension.class)
public class FormatterTest {
    @Test
    @DisplayName("should return listUserOrderEntity when the file has only one line")
    void shouldReturnListUserOrderEntityWhenFileHasOnlyOneLine ()  {

       String dateFormatter = Formatter.dateformat("yyyy-MM-dd", new Date());

        Assertions.assertEquals(dateFormatter, "2023-09-09");
    }

    @Test
    @DisplayName("should return listUserOrderEntity when the file has only one line")
    void a ()  {

        String decimalFormatter = Formatter.decimalFormat("#.00",2300.30, true);

        Assertions.assertEquals(decimalFormatter, "2300.30");
    }

    @Test
    @DisplayName("should return listUserOrderEntity when the file has only one line")
    void ab ()  {

        String decimalFormatter = Formatter.decimalFormat("#.00",2300.30, false);

        Assertions.assertEquals(decimalFormatter, "2300,30");
    }
}
