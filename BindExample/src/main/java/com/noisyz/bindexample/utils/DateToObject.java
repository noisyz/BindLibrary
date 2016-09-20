package com.noisyz.bindexample.utils;

import com.noisyz.bindlibrary.conversion.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateToObject implements Converter<String,Date> {

   private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

    @Override
    public Date getConvertedValue(String s) {
        Date date = null;
        try {
            date =  DATE_FORMAT.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
