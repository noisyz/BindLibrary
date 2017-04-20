package com.noisyz.bindlibrary.callback.layoutcallback;


/**
 * Created by oleg on 13.08.16.
 */
public class EmptyBaseLayoutResourceProvider<T> implements BaseLayoutResourceProvider<T>{

    private int mLayoutResID;

    public EmptyBaseLayoutResourceProvider(int mLayoutResID) {
        this.mLayoutResID = mLayoutResID;
    }


    @Override
    public int getLayoutCount() {
        return 1;
    }

    @Override
    public int getItemViewType(T t) {
        return 0;
    }

    @Override
    public int getLayoutResourceID(int type) {
        return mLayoutResID;
    }
}
