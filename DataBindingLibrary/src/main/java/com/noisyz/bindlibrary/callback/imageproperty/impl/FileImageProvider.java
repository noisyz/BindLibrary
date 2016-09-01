package com.noisyz.bindlibrary.callback.imageproperty.impl;

import com.bumptech.glide.Glide;
import com.noisyz.bindlibrary.callback.imageproperty.abs.AsyncImageProvider;

import java.io.File;

/**
 * Created by oleg on 29.08.16.
 */
public class FileImageProvider extends AsyncImageProvider<String> {
    @Override
    public void loadBitmap(String s) {
        Glide.with(getView().getContext()).load(new File(s)).dontAnimate().into(getView());
    }
}
