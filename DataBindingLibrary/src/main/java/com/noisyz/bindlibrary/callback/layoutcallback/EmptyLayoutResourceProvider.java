package com.noisyz.bindlibrary.callback.layoutcallback;

/**
 * Created by oleg on 26.08.16.
 */
public class EmptyLayoutResourceProvider implements LayoutResourceProvider {

    private int layoutResId;

    public EmptyLayoutResourceProvider(int layoutResId) {
        this.layoutResId = layoutResId;
    }


    @Override
    public int getItemViewType(Object o) {
        return 0;
    }

    @Override
    public int getLayoutResourceID(int type) {
        return layoutResId;
    }
}
