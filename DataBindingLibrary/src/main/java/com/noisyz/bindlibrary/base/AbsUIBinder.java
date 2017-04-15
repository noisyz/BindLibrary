package com.noisyz.bindlibrary.base;

import com.noisyz.bindlibrary.models.key.Key;

/**
 * Created by Oleg on 24.03.2016.
 */
public abstract class AbsUIBinder<BO> implements UIBinder<BO> {

    private BO bo;
    private Key key;

    public AbsUIBinder(BO bo, Key key) {
        this.bo = bo;
        this.key = key;
    }

    @Override
    public void setBindObject(BO bo) {
        this.bo = bo;
    }

    @Override
    public BO getBindObject() {
        return bo;
    }

    public Key getBinderKey() {
        return key;
    }

    @Override
    public void release() {
        bo = null;
        key = null;
    }

}
