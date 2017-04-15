package com.noisyz.bindlibrary.wrappers;

/**
 * Created by nero232 on 14.04.17.
 */

public interface ValueProvider<BO, T> {

    T invokeGetter(BO bo);

    void invokeSetter(BO bo, T t);

}
