package com.noisyz.databindinglibrary.conversion;

/**
 * Created by Oleg on 18.03.2016.
 */
public class EmptyConverter extends TwoWayConverter implements Converter{
    @Override
    public Object getConvertedValueToUI(Object o) {
        return o;
    }

    @Override
    public Object getConvertedValueToObject(Object o) {
        return o;
    }

    @Override
    public Object getConvertedValue(Object o) {
        return o;
    }
}
