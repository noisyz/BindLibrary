package com.noisyz.bindlibrary.callback.imageproperty.abs;

import android.graphics.Bitmap;

import com.noisyz.bindlibrary.callback.imageproperty.ImageProvider;

/**
 * Created by Oleg on 18.03.2016.
 */
public abstract class SyncImageProvider<T> extends ImageProvider<T> {

    @Override
    public void loadBitmap(T t) {
        showBitmap(getLoadedBitmap(t));
    }

    public abstract Bitmap getLoadedBitmap(T t);
}
