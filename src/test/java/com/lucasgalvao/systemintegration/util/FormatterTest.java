package com.lucasgalvao.systemintegration.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;


@ExtendWith(MockitoExtension.class)
public class FormatterTest {
    @Test
    @DisplayName("should return data in the format yyyy-MM-dd")
    void shouldReturnDataInTheFormatYYYYMMDD ()  {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

       String dateFormatter = Formatter.dateformat("yyyy-MM-dd", new Date());

       String year = String.valueOf(calendar.get(Calendar.YEAR));
       String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
       month = month.length() == 1 ? "0"+ month : month;
       String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

       String resultExpect = String.format("%s-%s-%s", year, month, day);

        System.out.println(resultExpect);


        Assertions.assertEquals(dateFormatter, resultExpect);
    }

    @Test
    @DisplayName("should return the number with two decimal places after the comma and with a period replacing the comma")
    void shouldReturnTheNumberWithTwoDecimalPlacesAfterCommaAndWithPeriodReplacingTheComma ()  {

        String decimalFormatter = Formatter.decimalFormat("#.00",2300.30, true);

        Assertions.assertEquals(decimalFormatter, "2300.30");
    }

    @Test
    @DisplayName("should return the number with two decimal places after the decimal point")
    void shouldReturnTheNumberWithTwoDecimalPlacesAftterDecimalPoint ()  {

        String decimalFormatter = Formatter.decimalFormat("#.00",2300.30, true);

        Assertions.assertEquals("2300.30", decimalFormatter);
    }
}
