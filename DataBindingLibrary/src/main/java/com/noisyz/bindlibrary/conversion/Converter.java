package com.noisyz.bindlibrary.conversion;

/**
 * Created by Oleg on 17.03.2016.
 */
public interface Converter<T, V>{

     V getConvertedValue(T t);

}
