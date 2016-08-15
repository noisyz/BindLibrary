package com.noisyz.databindinglibrary.conversion;

/**
 * Created by Oleg on 17.03.2016.
 */
public abstract class TwoWayConverter<T extends Object, V extends Object> {

    private Converter converterToUi = new Converter<T, V>() {
        @Override
        public V getConvertedValue(T t) {
            return getConvertedValueToUI(t);
        }
    };

    private Converter converterToObject = new Converter<V, T>() {
        @Override
        public T getConvertedValue(V v) {
            return getConvertedValueToObject(v);
        }
    };

    public abstract V getConvertedValueToUI(T t);

    public abstract T getConvertedValueToObject(V v);

    public Converter getConverterToUi(){
        return converterToUi;
    }

    public Converter getConverterToObject(){
        return converterToObject;
    }
}
