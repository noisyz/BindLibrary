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
    private List<AbsUIBinder> childBinders;

    public TreeUIBinder(Object object, ParentBinder parentBinder) {
        super(object, object.getClass().getName());
        this.parentBinder = parentBinder;
    }

    public TreeUIBinder(Object object){
        this(object, null);
    }

    @Override
    public void bindUI() {
        for (AbsUIBinder absUIBinder : childBinders) {
            absUIBinder.bindUI();
        }
    }

    @Override
    public TreeUIBinder setDataUpdatedCallback(DataUpdatedCallback callback) {
        super.setDataUpdatedCallback(callback);
        for (AbsUIBinder absUIBinder : childBinders) {
            absUIBinder.setDataUpdatedCallback(callback);
        }
        return this;
    }

    @Override
    public TreeUIBinder setDataUpdatedCallback(String propertyKey, DataUpdatedCallback callback) {
        for (AbsUIBinder absUIBinder : childBinders) {
            if (absUIBinder.getBinderKey().equals(propertyKey))
                absUIBinder.setDataUpdatedCallback(callback);
        }
        return this;
    }

    @Override
    public List<AbsUIBinder> getChildList() {
        return childBinders;
    }


    @Override
    public AbsUIBinder getChild(String propertyKey) {
        for (AbsUIBinder absUIBinder : childBinders) {
            if (absUIBinder.getBinderKey().equals(propertyKey))
                return absUIBinder;
        }
        return null;
    }

    @Override
    public TreeUIBinder addChild(AbsUIBinder absUIBinder) {
        initChildList();
        childBinders.add(absUIBinder);
        return this;
    }

    @Override
    public TreeUIBinder addChildCollection(List<AbsUIBinder> absUIBinders) {
        initChildList();
        childBinders.addAll(absUIBinders);
        return this;
    }


    @Override
    public void setObject(Object object) {
        super.setObject(object);
        for(AbsUIBinder absUIBinder:childBinders)
            absUIBinder.setObject(object);
    }

    @Override
    public void release(){
        super.release();
        parentBinder = null;
        childBinders.clear();
        childBinders = null;
    }

    private void initChildList(){
        if (childBinders == null) {
            childBinders = new ArrayList<>();
        }
    }

    public ParentBinder getParentBinder() {
        return parentBinder;
    }

    public boolean isRoot() {
        return parentBinder == null;
    }
}
