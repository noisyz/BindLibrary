package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.callback.DataUpdatedCallback;

/**
 * Created by Oleg on 18.03.2016.
 */
public interface UIBinder {

    void bindUI();

    UIBinder setDataUpdatedCallback(DataUpdatedCallback callback);

    DataUpdatedCallback getDataUpdatedCallback();

    boolean hasDataUpdatedCallback();

    void setObject(Object object);

    void release();

}
