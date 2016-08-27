package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.callback.DataUpdatedCallback;

/**
 * Created by Oleg on 24.03.2016.
 */
public abstract class AbsUIBinder implements UIBinder {

    protected Object object;
    private DataUpdatedCallback dataUpdatedCallback;

    public AbsUIBinder(Object object){
        this.object = object;
    }

    public AbsUIBinder setDataUpdatedCallback(DataUpdatedCallback callback) {
        this.dataUpdatedCallback = callback;
        return this;
    }

    @Override
    public void release() {
        dataUpdatedCallback = null;
    }

    protected void onObjectUpdated(Object object, String propertyName, Object value) {
        if (dataUpdatedCallback != null) {
            dataUpdatedCallback.onDataUpdated(this, object, propertyName, value);
        }
    }

    public boolean hasDataUpdatedCallback() {
        return dataUpdatedCallback != null;
    }

    @Override
    public void setObject(Object object) {
        this.object = object;
    }

    public DataUpdatedCallback getDataUpdatedCallback() {
        return dataUpdatedCallback;
    }

}
