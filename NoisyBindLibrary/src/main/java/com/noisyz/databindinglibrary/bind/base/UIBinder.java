package com.noisyz.databindinglibrary.bind.base;

import com.noisyz.databindinglibrary.callback.DataUpdatedCallback;

/**
 * Created by Oleg on 18.03.2016.
 */
public interface UIBinder<O extends Object> {

    void bindUI();

    UIBinder setDataUpdatedCallback(DataUpdatedCallback callback);

    DataUpdatedCallback getDataUpdatedCallback();

    boolean hasDataUpdatedCallback();

    void setObject(O o);

    void release();
}
