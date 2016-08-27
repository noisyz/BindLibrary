package com.noisyz.bindlibrary.callback.imageproperty.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.noisyz.bindlibrary.callback.imageproperty.abs.SyncImageProvider;

/**
 * Created by Oleg on 05.04.2016.
 */
public class FileImageProvider extends SyncImageProvider<String> {

    @Override
    public Bitmap getLoadedBitmap(String s) {
        return BitmapFactory.decodeFile(s);
    }
}
