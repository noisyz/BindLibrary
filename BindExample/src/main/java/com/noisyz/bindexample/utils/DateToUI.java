package com.noisyz.bindexample.utils;

import com.noisyz.bindlibrary.conversion.Converter;
import com.noisyz.bindlibrary.conversion.TwoWayConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateToUI implements Converter<Date,String> {

   private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

    @Override
    public String getConvertedValue(Date date) {
        return DATE_FORMAT.format(date);
    }
}
