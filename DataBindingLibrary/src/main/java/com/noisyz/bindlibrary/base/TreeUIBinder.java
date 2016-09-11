package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.callback.DataUpdatedCallback;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.ObjectBinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by oleg on 01.09.16.
 */
public class TreeUIBinder extends ParentBinder<List<AbsUIBinder>> {

    private ParentBinder parentBinder;

    public TreeUIBinder(Object object, ParentBinder parentBinder) {
        super(object, object.getClass().getName());
        this.parentBinder = parentBinder;
    }

    public TreeUIBinder(Object object){
        this(object, null);
    }

    @Override
    public void bindUI() {
        for (AbsUIBinder absUIBinder : getChildren()) {
            absUIBinder.bindUI();
        }
    }

    @Override
    public TreeUIBinder setDataUpdatedCallback(DataUpdatedCallback callback) {
        super.setDataUpdatedCallback(callback);
        for (AbsUIBinder absUIBinder : getChildren()) {
            absUIBinder.setDataUpdatedCallback(callback);
        }
        return this;
    }

    @Override
    public void initChildrenData() {
        if (children == null) {
            children = new ArrayList<>();
        }
    }

    @Override
    public TreeUIBinder setDataUpdatedCallback(String propertyKey, DataUpdatedCallback callback) {
        for (AbsUIBinder absUIBinder : getChildren()) {
            if (absUIBinder.getBinderKey().equals(propertyKey))
                absUIBinder.setDataUpdatedCallback(callback);
        }
        return this;
    }

    @Override
    public AbsUIBinder getChild(String propertyKey) {
        for (AbsUIBinder absUIBinder : getChildren()) {
            if (absUIBinder.getBinderKey().equals(propertyKey))
                return absUIBinder;
        }
        return null;
    }

    @Override
    public TreeUIBinder addChild(AbsUIBinder absUIBinder) {
        initChildrenData();
        getChildren().add(absUIBinder);
        return this;
    }

    @Override
    public TreeUIBinder addChildren(List<AbsUIBinder> absUIBinders) {
        initChildrenData();
        getChildren().addAll(absUIBinders);
        return this;
    }


    @Override
    public void setObject(Object object) {
        super.setObject(object);
        for(AbsUIBinder absUIBinder:getChildren())
            absUIBinder.setObject(object);
    }

    @Override
    public void release(){
        super.release();
        parentBinder = null;
        getChildren().clear();
        children = null;
    }

    public ParentBinder getParentBinder() {
        return parentBinder;
    }

    public boolean isRoot() {
        return parentBinder == null;
    }
}
