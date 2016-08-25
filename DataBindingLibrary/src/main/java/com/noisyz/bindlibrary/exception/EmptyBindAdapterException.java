package com.noisyz.databindinglibrary.exception;

/**
 * Created by oleg on 14.08.16.
 */
public class EmptyBindAdapterException extends IllegalArgumentException {

    public EmptyBindAdapterException(){
        super("Use BindAdapter.setItemViewCount(int count) if you want create empty multi layout adapter.");
    }
}
