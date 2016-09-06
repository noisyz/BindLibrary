package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.callback.DataUpdatedCallback;


/**
 * Created by Oleg on 24.03.2016.
 */
public abstract class AbsUIBinder implements UIBinder {

    private Object object;
    private DataUpdatedCallback dataUpdatedCallback;
    private String binderKey;

    public AbsUIBinder(Object object, String binderKey) {
        this.object = object;
        this.binderKey = binderKey;
    }

    public AbsUIBinder setDataUpdatedCallback(DataUpdatedCallback callback) {
        this.dataUpdatedCallback = callback;
        return this;
    }

    @Override
    public void release() {
        object = null;
        dataUpdatedCallback = null;
    }

    public String getBinderKey(){
        return binderKey;
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

    @Override
    public Object getObject() {
        return object;
    }

    public DataUpdatedCallback getDataUpdatedCallback() {
        return dataUpdatedCallback;
    }

}
