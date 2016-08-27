package com.noisyz.bindlibrary.callback.layoutcallback;

/**
 * Created by oleg on 13.08.16.
 */
public interface LayoutResourceProvider<T> {

    int getItemViewType(T t);

    int getLayoutResourceID(int type);
}
