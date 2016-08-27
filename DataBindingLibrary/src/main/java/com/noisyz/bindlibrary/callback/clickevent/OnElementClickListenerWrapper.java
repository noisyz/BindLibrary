package com.noisyz.bindlibrary.callback.clickevent;

/**
 * Created by oleg on 13.08.16.
 */
public class OnElementClickListenerWrapper {
    private int[] ids;
    private OnElementClickListener onClickListener;

    public OnElementClickListenerWrapper(int elementId, OnElementClickListener onClickListener) {
        this.ids = new int[]{elementId};
        this.onClickListener = onClickListener;
    }

    public OnElementClickListenerWrapper(int[] elementIds, OnElementClickListener onClickListener) {
        this.ids = elementIds;
        this.onClickListener = onClickListener;
    }

    public OnElementClickListener getOnClickListener() {
        return onClickListener;
    }

    public int[] getIds() {
        return ids;
    }
}
