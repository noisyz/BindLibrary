package com.noisyz.databindinglibrary.callback.imageproperty.abs;

import android.graphics.Bitmap;

import com.noisyz.databindinglibrary.callback.imageproperty.ImageProvider;

/**
 * Created by Oleg on 18.03.2016.
 */
public abstract class SyncImageProvider<T extends Object> extends ImageProvider<T> {

    public void loadBitmap(T t) {
        showBitmap(getLoadedBitmap(t));
    }

    public abstract Bitmap getLoadedBitmap(T t);
}
