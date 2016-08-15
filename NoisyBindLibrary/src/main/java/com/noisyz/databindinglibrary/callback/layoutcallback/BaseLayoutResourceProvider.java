package com.noisyz.databindinglibrary.callback.layoutcallback;

import com.noisyz.databindinglibrary.beans.abs.MultiTypeObject;

/**
 * Created by oleg on 13.08.16.
 */
public class BaseLayoutResourceProvider<O extends MultiTypeObject> implements LayoutResourceProvider<O> {

    private int mLayoutResID;

    public BaseLayoutResourceProvider(int mLayoutResID) {
        this.mLayoutResID = mLayoutResID;
    }

    @Override
    public int getLayoutResourceID(int type) {
        return mLayoutResID;
    }
}
