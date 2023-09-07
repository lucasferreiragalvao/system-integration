package com.lucasgalvao.systemintegration.app.entrypoint.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    public static String dateformat(String format, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String decimalFormat(String format, double value, boolean replaceCommaWithDot){
        DecimalFormat d = new DecimalFormat(format);
        return replaceCommaWithDot ? d.format(value).replace(",", ".") : d.format(value);
    }
}
