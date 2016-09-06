package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.callback.DataUpdatedCallback;

import java.util.Collection;
import java.util.List;

/**
 * Created by oleg on 26.08.16.
 */
public abstract class ParentBinder<T> extends AbsUIBinder{
    public ParentBinder(Object object, String binderKey) {
        super(object, binderKey);
    }

    public abstract AbsUIBinder setDataUpdatedCallback(String propertyKey, DataUpdatedCallback callback);

    public abstract T getChildList();

    public abstract AbsUIBinder getChild(String propertyKey);

    public abstract ParentBinder addChild(AbsUIBinder absUIBinder);

    public abstract ParentBinder addChildCollection(T binders);

}
