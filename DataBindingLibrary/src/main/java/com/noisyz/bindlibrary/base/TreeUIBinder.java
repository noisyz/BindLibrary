package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.callback.DataUpdatedCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 01.09.16.
 */
public class TreeUIBinder extends AbsUIBinder implements ParentBinder{

    private TreeUIBinder parentBinder;
    private List<AbsUIBinder> childBinders;

    public TreeUIBinder(Object object, TreeUIBinder parentBinder) {
        this(object);
        this.parentBinder = parentBinder;
    }

    public TreeUIBinder(Object object) {
        super(object);
        childBinders = new ArrayList<>();
    }

    @Override
    public void bindUI() {
        for(AbsUIBinder absUIBinder:childBinders){
            absUIBinder.bindUI();
        }
    }

    @Override
    public TreeUIBinder setDataUpdatedCallback(DataUpdatedCallback callback){
        super.setDataUpdatedCallback(callback);
        for(AbsUIBinder absUIBinder:childBinders){
            absUIBinder.setDataUpdatedCallback(callback);
        }
        return this;
    }

    @Override
    public TreeUIBinder setDataUpdatedCallback(String propertyKey, DataUpdatedCallback callback) {
        for(AbsUIBinder absUIBinder:childBinders){
            absUIBinder.setDataUpdatedCallback(callback);
        }
        return this;
    }

    @Override
    public TreeUIBinder getParentBinder(){
        return parentBinder;
    }

    public boolean isRoot(){
        return parentBinder == null;
    }
}
