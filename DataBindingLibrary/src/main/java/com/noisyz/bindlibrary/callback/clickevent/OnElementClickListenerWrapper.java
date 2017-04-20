package com.noisyz.bindlibrary.callback.clickevent;

/**
 * Created by oleg on 13.08.16.
 */
public class OnElementClickListenerWrapper<T> {
    private int[] ids;
    private OnElementClickListener<T> onClickListener;

    public OnElementClickListenerWrapper(int elementId, OnElementClickListener<T> onClickListener) {
        this.ids = new int[]{elementId};
        this.onClickListener = onClickListener;
    }

    public OnElementClickListenerWrapper(int[] elementIds, OnElementClickListener<T> onClickListener) {
        this.ids = elementIds;
        this.onClickListener = onClickListener;
    }

    public OnElementClickListener<T> getOnClickListener() {
        return onClickListener;
    }

    public int[] getIds() {
        return ids;
    }
}
