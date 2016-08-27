package com.noisyz.bindexample.utils;

import com.noisyz.bindlibrary.conversion.TwoWayConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateConverter extends TwoWayConverter<Date, String> {

   private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

   @Override
   public String getConvertedValueToUI(Date date) {
      return DATE_FORMAT.format(date);
   }

   @Override
   public Date getConvertedValueToObject(String s) {
      Date date = null;
      try {
         date =  DATE_FORMAT.parse(s);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return date;
   }
}
