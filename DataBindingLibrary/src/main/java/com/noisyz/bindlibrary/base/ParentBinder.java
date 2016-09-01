package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.callback.DataUpdatedCallback;

/**
 * Created by oleg on 26.08.16.
 */
public interface ParentBinder {
    UIBinder setDataUpdatedCallback(String propertyKey, DataUpdatedCallback callback);

    UIBinder getParentBinder();
}
