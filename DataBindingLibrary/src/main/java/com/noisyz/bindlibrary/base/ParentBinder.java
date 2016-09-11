package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.callback.DataUpdatedCallback;

import java.util.Collection;
import java.util.List;

/**
 * Created by oleg on 26.08.16.
 * T can be a collection, array, map, set or another data structure
 */
public abstract class ParentBinder<T> extends AbsUIBinder{
    protected T children;

    public abstract void initChildrenData();

    public ParentBinder(Object object, String binderKey) {
        super(object, binderKey);
    }

    public abstract AbsUIBinder setDataUpdatedCallback(String propertyKey, DataUpdatedCallback callback);

    public T getChildren(){
        return children;
    };

    public abstract AbsUIBinder getChild(String propertyKey);

    public abstract ParentBinder addChild(AbsUIBinder absUIBinder);

    public abstract ParentBinder addChildren(T binders);
}
