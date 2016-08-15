package com.noisyz.databindinglibrary.conversion;

/**
 * Created by Oleg on 17.03.2016.
 */
public interface Converter<T extends Object, V extends Object>{

     V getConvertedValue(T t);

}
