package com.noisyz.databindinglibrary.callback.imageproperty.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.noisyz.databindinglibrary.callback.imageproperty.abs.SyncImageProvider;

/**
 * Created by Oleg on 05.04.2016.
 */
public class ResourceImageProvider extends SyncImageProvider<Integer> {
    @Override
    public Bitmap getLoadedBitmap(Integer integer) {
        return BitmapFactory.decodeResource(getView().getContext().getResources(), integer);
    }
}
